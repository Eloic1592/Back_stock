package itusolar.predict.dependency;

import itusolar.historique.HistoriqueManagerSignature;
import itusolar.historique.HistoryManager;
import itusolar.historique.filter.BloomFilter;
import itusolar.historique.filter.BloomFilterSignature;
import itusolar.jirama.HTarifManager;
import itusolar.jirama.TarifManager;
import itusolar.lieu.BuildingManager;
import itusolar.lieu.BuildingManagerSignature;
import itusolar.lieu.SectionManager;
import itusolar.lieu.SectionManagerSignature;
import itusolar.predict.Predicter;
import itusolar.predict.etatconsommation.ConsumptionStateEvaluator;
import itusolar.predict.etatconsommation.ConsumptionStateEvaluatorSignature;
import itusolar.predict.historique.HistoriquePredicterSignature;
import itusolar.predict.historique.PredictOptimized;
import itusolar.predict.historique.classificater.HistoriqueClassificator;
import itusolar.predict.historique.classificater.HistoriqueClassificatorSignature;
import itusolar.predict.historique.model.*;
import itusolar.predict.loader.Loader;
import itusolar.predict.loader.LoaderSignature;
import itusolar.predict.loader.MeteoLoaderOptimized;
import itusolar.prepare.schedule.ScheduleAccessor;
import itusolar.prepare.schedule.ScheduleAccessorSignature;
import itusolar.prepare.schedule.predict.ScheduleDateFilter;
import itusolar.simulation.SimulationManager;
import itusolar.simulation.SimulationScheduler;
import itusolar.simulation.SimulationSignature;
import itusolar.simulation.material.MaterialManager;
import itusolar.simulation.material.MaterialManagerSignature;
import itusolar.simulation.classification.*;
import itusolar.simulation.consumption.ConsumptionEvaluator;
import itusolar.simulation.consumption.ConsumptionEvaluatorSignature;
import itusolar.simulation.filter.DataIgnore;
import itusolar.simulation.filter.DataIgnoreSignature;
import itusolar.simulation.preparation.*;
import itusolar.source.SectionCapacityManager;
import itusolar.source.SectionCapacityManagerSignature;

public class PredictDependency {
    public final static int PREDICTORY = 0;
    public final static int SCHEDULE = 50;
    Predicter predicter;
    SimulationSignature simulationSignature;
    ConsumptionEvaluatorSignature consumptionEvaluator;
    HistoriqueFilterSignature storyFilter;
    HistoriqueClassificatorSignature storyClassificator;
    PlaceClassifierSignature placeClassifier;
    ConsumptionStateEvaluatorSignature consumptionStateEvaluator;
    DataPreparatorSignature dataPreparator;
    SimulDataClassifierSignature dataClassifier;
    SimulDataMotherClassifierSignature jiramaClassifier;
    SimulDataMotherClassifierSignature solarClassifier;
    DatePreparatorSignature datePreparator;
    HTarifManager tarifManager;
    HistoriquePredicterSignature storyPredictor;
    HistoriqueManagerSignature historyManager;
    SectionManagerSignature sectionManager;
    HistoriqueModel historyModel;
    ModelPredictorSignature modelPredictor;
    LoaderSignature loader;
    BuildingManagerSignature lieuManager;
    MaterialManagerSignature materialManager;
    SectionCapacityManagerSignature sectionCapacityManager;
    DataIgnoreSignature dataIgnore;
    ScheduleAccessorSignature accessor;
    itusolar.prepare.schedule.predict.SchedulePredictorSignature predictor;
    itusolar.prepare.schedule.predict.ScheduleDateFilterSignature dateFilter;
    itusolar.prepare.schedule.predict.SchedulePreparatorySignature schedulePreparatory;
    BloomFilterSignature bloomFilter;
    boolean onProd = false;

    public PredictDependency(int state) {
        this(state, true);
    }

    public PredictDependency(int  state, boolean onProd) {
        this.setOnProd(onProd);
        this.setStoryFilter(new HistoriqueFilter());
        this.setPlaceClassifier(new PlaceClassifier());
        this.setConsumptionStateEvaluator(new ConsumptionStateEvaluator(new HistoriqueClassificator()));
        this.setConsumptionEvaluator(new ConsumptionEvaluator(this.getStoryFilter(),this.getPlaceClassifier(),
                this.getConsumptionStateEvaluator()));
        this.setJiramaClassifier(new JiramaClassifier());
        this.setSolarClassifier(new SolarClassifier());
        this.setDataClassifier(new SimulDataClassifier(this.getJiramaClassifier(),this.getSolarClassifier()));
        this.setDataPreparator(new MidNightDataPreparatory(this.getDataClassifier(),this.getPlaceClassifier()));
        this.setDatePreparator(new MidNightPreparatory(this.getDataPreparator()));
        this.setAccessor(new ScheduleAccessor());
        this.setDateFilter(new ScheduleDateFilter(this.getAccessor()));
        this.setSchedulePreparatory(new itusolar.prepare.schedule.predict.SchedulePreparatory(this.getDateFilter()));
        this.loadSimulation(state);
        this.setTarifManager(new TarifManager());
        this.setHistoryManager(new HistoryManager());
        this.setSectionManager(new SectionManager());
        this.setStoryClassificator(new HistoriqueClassificator());
        this.setHistoryModel(new MeanModel(this.getStoryClassificator(),this.getStoryFilter()));
        this.setModelPredictor(new ModelPredictor(this.getStoryFilter()));
        this.setStoryPredictor(new PredictOptimized(this.getHistoryManager(),this.getSectionManager(),
                this.getHistoryModel(),this.getModelPredictor()));
        this.setLieuManager(new BuildingManager(this.getSectionManager()));
        this.setMaterialManager(new MaterialManager());
        this.setSectionCapacityManager(new SectionCapacityManager());
        this.initLoader(state);
        ((HistoryManager)this.getHistoryManager()).setLoader(this.getLoader());
        this.setBloomFilter(new BloomFilter(2000000, .01));
        ((HistoryManager)this.getHistoryManager()).setBloomFilter(this.getBloomFilter());
        this.setDataIgnore(new DataIgnore());
        this.setPredicter(new Predicter(this.getSimulationSignature(),this.getTarifManager(),this.getStoryPredictor(),this.getLoader(),this.getDataIgnore(),this.getConsumptionStateEvaluator()));
    }

    public BloomFilterSignature getBloomFilter() {
        return bloomFilter;
    }

    public void setBloomFilter(BloomFilterSignature bloomFilter) {
        this.bloomFilter = bloomFilter;
    }

    public itusolar.prepare.schedule.predict.ScheduleDateFilterSignature getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(itusolar.prepare.schedule.predict.ScheduleDateFilterSignature dateFilter) {
        this.dateFilter = dateFilter;
    }

    public ScheduleAccessorSignature getAccessor() {
        return accessor;
    }

    public void setAccessor(ScheduleAccessorSignature accessor) {
        this.accessor = accessor;
    }

    public itusolar.prepare.schedule.predict.SchedulePredictorSignature getPredictor() {
        return predictor;
    }

    public void setPredictor(itusolar.prepare.schedule.predict.SchedulePredictorSignature predictor) {
        this.predictor = predictor;
    }

    public itusolar.prepare.schedule.predict.SchedulePreparatorySignature getSchedulePreparatory() {
        return schedulePreparatory;
    }

    public void setSchedulePreparatory(itusolar.prepare.schedule.predict.SchedulePreparatorySignature schedulePreparatory) {
        this.schedulePreparatory = schedulePreparatory;
    }

    public void loadSimulation(int isPredicter) {
        if (isPredicter == PredictDependency.SCHEDULE) {
            this.setSimulationSignature(new SimulationManager(this.getConsumptionEvaluator(), this.getDataPreparator(),
                    this.getDatePreparator()));
        } else {
            this.setSimulationSignature(new SimulationScheduler(null,this.getSchedulePreparatory(), this.getConsumptionEvaluator(), this.getDataPreparator(), this.getDatePreparator()));
        }
    }

    public void initLoader(int isPredicted) {
        if (this.isOnProd()) {
            this.setLoader(new MeteoLoaderOptimized(this.getLieuManager(), this.getMaterialManager(), this.getSectionCapacityManager(), this.getSectionManager()));
        } else {
            this.setLoader(new Loader(this.getLieuManager(), this.getMaterialManager(), this.getSectionCapacityManager(), this.getSectionManager()));
        }
        if (isPredicted == PredictDependency.PREDICTORY)
            ((SimulationScheduler)this.getSimulationSignature()).setLoader(this.getLoader());
    }

    public boolean isOnProd() {
        return onProd;
    }

    public void setOnProd(boolean onProd) {
        this.onProd = onProd;
    }

    public void refreshSimulData() {
        this.getStoryPredictor().refresh();
    }

    public DataIgnoreSignature getDataIgnore() {
        return dataIgnore;
    }

    public void setDataIgnore(DataIgnoreSignature dataIgnore) {
        this.dataIgnore = dataIgnore;
    }

    public SectionCapacityManagerSignature getSectionCapacityManager() {
        return sectionCapacityManager;
    }

    public void setSectionCapacityManager(SectionCapacityManagerSignature sectionCapacityManager) {
        this.sectionCapacityManager = sectionCapacityManager;
    }

    public MaterialManagerSignature getMaterialManager() {
        return materialManager;
    }

    public void setMaterialManager(MaterialManagerSignature materialManager) {
        this.materialManager = materialManager;
    }

    public BuildingManagerSignature getLieuManager() {
        return lieuManager;
    }

    public void setLieuManager(BuildingManagerSignature lieuManager) {
        this.lieuManager = lieuManager;
    }

    public LoaderSignature getLoader() {
        return loader;
    }

    public void setLoader(LoaderSignature loader) {
        this.loader = loader;
    }

    public ModelPredictorSignature getModelPredictor() {
        return modelPredictor;
    }

    public void setModelPredictor(ModelPredictorSignature modelPredictor) {
        this.modelPredictor = modelPredictor;
    }

    public HistoriqueModel getHistoryModel() {
        return historyModel;
    }

    public void setHistoryModel(HistoriqueModel historyModel) {
        this.historyModel = historyModel;
    }

    public SectionManagerSignature getSectionManager() {
        return sectionManager;
    }

    public void setSectionManager(SectionManagerSignature sectionManager) {
        this.sectionManager = sectionManager;
    }

    public HistoriqueManagerSignature getHistoryManager() {
        return historyManager;
    }

    public void setHistoryManager(HistoriqueManagerSignature historyManager) {
        this.historyManager = historyManager;
    }

    public HistoriquePredicterSignature getStoryPredictor() {
        return storyPredictor;
    }

    public void setStoryPredictor(HistoriquePredicterSignature storyPredictor) {
        this.storyPredictor = storyPredictor;
    }

    public HTarifManager getTarifManager() {
        return tarifManager;
    }

    public void setTarifManager(HTarifManager tarifManager) {
        this.tarifManager = tarifManager;
    }

    public DatePreparatorSignature getDatePreparator() {
        return datePreparator;
    }

    public void setDatePreparator(DatePreparatorSignature datePreparator) {
        this.datePreparator = datePreparator;
    }

    public SimulDataMotherClassifierSignature getJiramaClassifier() {
        return jiramaClassifier;
    }

    public void setJiramaClassifier(SimulDataMotherClassifierSignature jiramaClassifier) {
        this.jiramaClassifier = jiramaClassifier;
    }

    public SimulDataMotherClassifierSignature getSolarClassifier() {
        return solarClassifier;
    }

    public void setSolarClassifier(SimulDataMotherClassifierSignature solarClassifier) {
        this.solarClassifier = solarClassifier;
    }

    public SimulDataClassifierSignature getDataClassifier() {
        return dataClassifier;
    }

    public void setDataClassifier(SimulDataClassifierSignature dataClassifier) {
        this.dataClassifier = dataClassifier;
    }

    public DataPreparatorSignature getDataPreparator() {
        return dataPreparator;
    }

    public void setDataPreparator(DataPreparatorSignature dataPreparator) {
        this.dataPreparator = dataPreparator;
    }

    public HistoriqueClassificatorSignature getStoryClassificator() {
        return storyClassificator;
    }

    public void setStoryClassificator(HistoriqueClassificatorSignature storyClassificator) {
        this.storyClassificator = storyClassificator;
    }

    public ConsumptionStateEvaluatorSignature getConsumptionStateEvaluator() {
        return consumptionStateEvaluator;
    }

    public void setConsumptionStateEvaluator(ConsumptionStateEvaluatorSignature consumptionStateEvaluator) {
        this.consumptionStateEvaluator = consumptionStateEvaluator;
    }

    public PlaceClassifierSignature getPlaceClassifier() {
        return placeClassifier;
    }

    public void setPlaceClassifier(PlaceClassifierSignature placeClassifier) {
        this.placeClassifier = placeClassifier;
    }

    public ConsumptionEvaluatorSignature getConsumptionEvaluator() {
        return consumptionEvaluator;
    }

    public HistoriqueFilterSignature getStoryFilter() {
        return storyFilter;
    }

    public void setStoryFilter(HistoriqueFilterSignature storyFilter) {
        this.storyFilter = storyFilter;
    }

    public void setConsumptionEvaluator(ConsumptionEvaluatorSignature consumptionEvaluator) {
        this.consumptionEvaluator = consumptionEvaluator;
    }

    public Predicter getPredicter() {
        return predicter;
    }

    public void setPredicter(Predicter predicter) {
        this.predicter = predicter;
    }

    public SimulationSignature getSimulationSignature() {
        return simulationSignature;
    }

    public void setSimulationSignature(SimulationSignature simulationSignature) {
        this.simulationSignature = simulationSignature;
    }
}
