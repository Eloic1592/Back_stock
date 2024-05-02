package itusolar.historique.confirmation;

import bean.CGenUtil;
import itusolar.historique.*;
import itusolar.historique.filter.BloomFilterSignature;
import itusolar.predict.loader.LoaderSignature;
import itusolar.simulation.DateConcern;

import java.sql.Connection;

public class HistoryValidator extends HistoryManager implements HistoryValidatorSignature {
    public HistoryValidator(LoaderSignature loader, BloomFilterSignature bloomFilter) {
        super(loader, bloomFilter);
    }

    @Override
    public void validate(ValidatorParams params, Connection connection) throws Exception {
        boolean verif = connection == null || connection.isClosed();
        connection = this.getConnection(connection);
        HistTemporary[] tempList = this.search(new HistTemporary(), params.where(), connection);
        this.validate(tempList, connection);
        if (verif)
            connection.close();
    }

    @Override
    public void validate(HistTemporary[] params, Connection connection) throws Exception {
        DateConcern[] dateConcerns = new DateConcern[params.length];
        for (int i = 0; i < dateConcerns.length; i++)
            dateConcerns[i] = new DateConcern(params[i].getDatins());
        this.getLoader().loadTemperature(dateConcerns, connection);
        for (int i = 0; i < dateConcerns.length; i++) {
            params[i].setTemperature(dateConcerns[i].getTemperature());
            this.createSimple(params[i].toHistEnergy(), connection);
            params[i].deleteToTable(connection);
        }
    }

    public HistTemporary[] search(HistTemporary histEnergy, String where, Connection connection) throws Exception {
        String[] params = {};
        Object[] datas = CGenUtil.rechercher(histEnergy, params, params, connection, where);
        HistTemporary[] tempList = new HistTemporary[datas.length];
        for (int i = 0; i < datas.length; i++) {
            tempList[i] = (HistTemporary) datas[i];
        }
        return tempList;
    }
}
