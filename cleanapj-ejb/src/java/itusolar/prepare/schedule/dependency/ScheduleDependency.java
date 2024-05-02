package itusolar.prepare.schedule.dependency;

import itusolar.predict.PredicterSignature;
import itusolar.predict.dependency.PredictDependency;
import itusolar.prepare.schedule.ScheduleAccessor;
import itusolar.prepare.schedule.ScheduleAccessorSignature;
import itusolar.prepare.schedule.predict.ScheduleDateFilterSignature;

public class ScheduleDependency {
    ScheduleAccessorSignature accessor;
    itusolar.prepare.schedule.predict.SchedulePredictorSignature predictor;
    itusolar.prepare.schedule.predict.SchedulePreparatorySignature preparatory;
    itusolar.prepare.schedule.predict.ScheduleDateFilterSignature dateFilter;
    PredicterSignature dataPredictor;
    PredictDependency predictDependency;

    public ScheduleDependency() {
        this(true);
    }

    public ScheduleDependency(boolean verif) {
        this.setAccessor(new ScheduleAccessor());
        this.setDateFilter(new itusolar.prepare.schedule.predict.ScheduleDateFilter(this.getAccessor()));
        this.setPreparatory(new itusolar.prepare.schedule.predict.SchedulePreparatory(this.getDateFilter()));
        this.setPredictDependency(new PredictDependency(PredictDependency.SCHEDULE, verif));
        this.setDataPredictor(this.getPredictDependency().getPredicter());
        this.setPredictor(new itusolar.prepare.schedule.predict.SchedulePredictor(this.getPreparatory(),this.getDataPredictor()));
    }

    public PredictDependency getPredictDependency() {
        return predictDependency;
    }

    public void setPredictDependency(PredictDependency predictDependency) {
        this.predictDependency = predictDependency;
    }

    public PredicterSignature getDataPredictor() {
        return dataPredictor;
    }

    public void setDataPredictor(PredicterSignature dataPredictor) {
        this.dataPredictor = dataPredictor;
    }

    public itusolar.prepare.schedule.predict.ScheduleDateFilterSignature getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(ScheduleDateFilterSignature dateFilter) {
        this.dateFilter = dateFilter;
    }

    public itusolar.prepare.schedule.predict.SchedulePreparatorySignature getPreparatory() {
        return preparatory;
    }

    public void setPreparatory(itusolar.prepare.schedule.predict.SchedulePreparatorySignature preparatory) {
        this.preparatory = preparatory;
    }

    public itusolar.prepare.schedule.predict.SchedulePredictorSignature getPredictor() {
        return predictor;
    }

    public void setPredictor(itusolar.prepare.schedule.predict.SchedulePredictorSignature predictor) {
        this.predictor = predictor;
    }

    public ScheduleAccessorSignature getAccessor() {
        return accessor;
    }

    public void setAccessor(ScheduleAccessorSignature accessor) {
        this.accessor = accessor;
    }
}
