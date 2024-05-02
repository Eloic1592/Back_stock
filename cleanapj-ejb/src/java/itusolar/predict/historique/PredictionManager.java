package itusolar.predict.historique;

import itusolar.historique.HistEnergyCalc;
import itusolar.historique.HistoriqueManagerSignature;
import itusolar.lieu.Section;
import itusolar.lieu.SectionManagerSignature;
import itusolar.predict.historique.model.HistoriqueModel;
import itusolar.predict.historique.model.ModelPredictorSignature;
import itusolar.prepare.HServiceManager;
import itusolar.simulation.DateConcern;
import utilitaire.UtilDB;

import java.sql.Connection;

public class PredictionManager extends HServiceManager implements HistoriquePredicterSignature {
    HistoriqueManagerSignature historiqueManager;
    SectionManagerSignature sectionManager;
    HistoriqueModel historiqueModel;

    ModelPredictorSignature predictor;

    public PredictionManager(HistoriqueManagerSignature historiqueManager, SectionManagerSignature sectionManager,
                             HistoriqueModel historiqueModel, ModelPredictorSignature predictor) {
        this.historiqueManager = historiqueManager;
        this.sectionManager = sectionManager;
        this.historiqueModel = historiqueModel;
        this.predictor = predictor;
    }

    @Override
    public HistMean[] predict(HistEnergyCalc[] historiques, DateConcern[] dateConcerns, Section[] lieux) throws Exception {
        HistFinalPred[] histFinalCompleted = this.historiqueModel.fit(historiques, lieux);
        return this.predict(dateConcerns, histFinalCompleted);
    }

    @Override
    public HistMean[] predict(DateConcern[] dateConcerns, Connection connection) throws Exception {
        connection =(connection == null)? new UtilDB().GetConn() : connection;
        HistEnergyCalc[] historiques = this.historiqueManager.getAll(connection);
        Section[] lieux = this.sectionManager.getAll(connection);
        return this.predict(historiques,dateConcerns,lieux);
    }

    @Override
    public HistMean[] predict(DateConcern[] dateConcerns, HistFinalPred[] pred) throws Exception {
        return this.predictor.predict(pred,dateConcerns);
    }

    @Override
    public void refresh() {
         // TODO Auto-generated method stub
    }
}
