package itusolar.historique;

import bean.CGenUtil;
import itusolar.historique.filter.BloomFilterSignature;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.HServiceManager;
import itusolar.simulation.DateConcern;
import utilitaire.UtilDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Vector;

/**
 * The type Historique manager.
 */
public class HistoryManager extends HServiceManager implements HistoriqueManagerSignature {
    /**
     * Search object [ ]. calcule les statistiques(energie stoquée, énergie restante) entre une intervalle de date.
     *
     * @param user       the user
     * @param start      the start
     * @param end        the end
     * @param connection the connection
     * @param afterWhere the after where
     * @return the object [ ]
     * @throws Exception the exception
     */
    LoaderSignature loader;
    BloomFilterSignature bloomFilter;

    public HistoryManager(LoaderSignature loader, BloomFilterSignature bloomFilter) {
        this.setLoader(loader);
        this.setBloomFilter(bloomFilter);
    }

    public BloomFilterSignature getBloomFilter() {
        return bloomFilter;
    }

    public void setBloomFilter(BloomFilterSignature bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    public HistoryManager() {
    }

    public LoaderSignature getLoader() {
        return loader;
    }

    public void setLoader(LoaderSignature loader) {
        this.loader = loader;
    }

    @Override
    public Object[] search(HistEnergyCalc histEnergy, HistoriqueParams params, Connection connection, String afterWhere) throws Exception {
        String col = "datins";
        String where = "and (";
        where += ((params.getStart() == null) ? " 1=1 " : String.format(" %s >= "+this.toTimestamp("%s"),col,params.getStart())) + " and ";
        where += (params.getEnd() == null) ? " 1=1 " : String.format(" %s <= "+this.toTimestamp("%s"),col,params.getEnd());
        where += ")";
        afterWhere += where;
        String[] intervals = {}, values = {};
        if (params.getPage() < 0) {
            return CGenUtil.rechercher(histEnergy, intervals, values, connection, afterWhere);
        }
        return CGenUtil.rechercherPage(histEnergy,intervals,values, params.getPage(), afterWhere,
                this.getConnection(connection), params.getRows());
    }


    /**
     * Eval hist energy calc [ ]. Calcul la durree, la puissance sortie et la puissance entree de chaque historique.
     *
     * @param c          the c
     * @param afterWhere the after where
     * @return the hist energy calc [ ]
     * @throws Exception the exception
     */
    public HistEnergyCalc[] eval(HistEnergyCalc histEnergy, HistoriqueParams params,Connection c,String afterWhere) throws Exception {
        HistEnergyCalc[] results = this.cast(this.search(histEnergy, params, c, afterWhere));
        HistEnergyCalc[][] histSplited = this.split(results);
        for (HistEnergyCalc[] hist:histSplited) {
            this.evalDuree(hist,params.getStart(),params.getEnd());
        }

        return results;
    }

    public HistEnergyCalc[][] split(HistEnergyCalc[] results) {
        Vector<Vector<HistEnergyCalc>> answersTemp = new Vector<>();
        Vector<HistEnergyCalc> insideTemp = null;
        int sourceid = -500;
        for (HistEnergyCalc result: results) {
            if (!result.getSourceid().equals(sourceid)) {
                if (insideTemp != null)
                    answersTemp.add(insideTemp);
                sourceid = result.getSourceid();
                insideTemp = new Vector<>();
            }
            assert insideTemp != null : String.format("Source null, verifier si l'un des source a un indice %s.",sourceid);
            insideTemp.add(result);
        }
        if (insideTemp != null)
            answersTemp.add(insideTemp);
        HistEnergyCalc[][] response = new HistEnergyCalc[answersTemp.size()][];
        int[] i = {0};
        answersTemp.forEach((answerTemp)-> {
            response[i[0]] = new HistEnergyCalc[answerTemp.size()];
            answerTemp.copyInto(response[i[0]++]);
        });
        return response;
    }
    /**
     * Eval duree.
     *
     * @param results the results
     * @param start   the start
     * @param end     the end
     */
    public void evalDuree(HistEnergyCalc[] results,Timestamp start,Timestamp end) {
        for (int i = 0; i < results.length-1; i++) {
            results[i].evalDuree(results[i+1].getDatins());
        }
        if (results.length > 0)
            results[results.length-1].evalDuree(end);
    }


    /**
     * Create.
     *
     * @param histEnergy the hist energy
     * @param connection the connection
     * @throws Exception the exception
     */
    public void create(HistEnergy histEnergy, Connection connection) throws Exception {
        this.loadTemperature(histEnergy, connection);
        this.createSimple(histEnergy, connection);
    }

    public void createSimple(HistEnergy histEnergy, Connection connection) throws Exception {
        histEnergy.check();
        this.getBloomFilter().addWhenOk(histEnergy);
        connection = (connection != null) ? connection : (new UtilDB()).GetConn();
        histEnergy.construirePK(connection);
        CGenUtil.save(histEnergy,connection);
        connection.commit();
    }

    public void loadTemperature(HistEnergy histEnergy, Connection connection) throws IOException {
        DateConcern[] concerns = {
                new DateConcern(histEnergy.getDatins())
        };
        this.loader.loadTemperature(concerns, connection);
        histEnergy.setTemperature(concerns[0].getTemperature());
    }

    /**
     * Cast hist energy calc [ ].
     *
     * @param datas the datas
     * @return the hist energy calc [ ]
     */
    public HistEnergyCalc[] cast(Object[] datas) {
        if (datas == null) return null;
        HistEnergyCalc[] result = new HistEnergyCalc[datas.length];
        for (int i = 0; i < datas.length; i++) {
            result[i] = (HistEnergyCalc) datas[i];
        }
        return result;
    }

    @Override
    public void create(HistoryParams params, Connection connection) throws Exception {
        for (int i = 0; i < params.getDatas().length; i++) {
            if (i == 0)
                connection = (new UtilDB()).GetConn();
            HistEnergy energy = params.getDatas()[i];
            energy.setMode("annul");
            this.create(energy, connection);
            if ((i % 100) == 0) {
                connection.close();
                connection = (new UtilDB()).GetConn();
            }
        }
    }

    @Override
    public HistEnergyCalc[] getAll(Connection connection) throws Exception {
        Object[] results = CGenUtil.rechercher(new HistEnergyCalc(),new String[0],new String[0],connection, "");
        return this.cast(results);
    }
}
