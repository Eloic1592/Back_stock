package itusolar.predict.historique;

import itusolar.historique.HistoriqueManagerSignature;
import itusolar.lieu.SectionManagerSignature;
import itusolar.predict.historique.model.HistoriqueModel;
import itusolar.predict.historique.model.ModelPredictorSignature;
import itusolar.simulation.DateConcern;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PredictOptimized extends PredictionManager {
    private HistFinalPred[] predictions;
    private Timestamp lastUpdate;
    private long interval = 3600000;

    public PredictOptimized(HistoriqueManagerSignature historizesManager, SectionManagerSignature sectionManager, HistoriqueModel historiqueModel, ModelPredictorSignature predictor) {
        super(historizesManager, sectionManager, historiqueModel, predictor);
    }

    @Override
    public HistMean[] predict(DateConcern[] dateConcerns, Connection connection) throws Exception {
        if (this.shouldLoad()) {
            return this.load(dateConcerns,connection);
        } else {
            return this.predict(dateConcerns,this.getPredictions());
        }
    }

    public HistMean[] load(DateConcern[] dateConcerns, Connection connection) throws Exception {
        this.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        return super.predict(dateConcerns,connection);
    }

    @Override
    public HistMean[] predict(DateConcern[] dateConcerns, HistFinalPred[] pred) throws Exception {
        this.setPredictions(pred);
        return super.predict(dateConcerns, pred);
    }

    public boolean shouldLoad() {
        if (this.getLastUpdate() == null || this.getPredictions() == null) {
            return true;
        }
        Timestamp temp = new Timestamp(this.getLastUpdate().getTime()+this.getInterval());
        return  (temp.after(Timestamp.valueOf(LocalDateTime.now())));

    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp last) {
        this.lastUpdate = last;
    }

    public HistFinalPred[] getPredictions() {
        return predictions;
    }

    public void setPredictions(HistFinalPred[] predictions) {
        this.predictions = predictions;
    }

    @Override
    public void refresh() {
        Timestamp temp = Timestamp.valueOf(LocalDateTime.now());
        temp = new Timestamp(temp.getTime()-this.getInterval());
        this.setLastUpdate(temp);
    }
}
