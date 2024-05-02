package itusolar.historique;

import itusolar.controller.*;
import itusolar.historique.confirmation.HistoryValidator;
import itusolar.historique.confirmation.HistoryValidatorSignature;
import itusolar.historique.confirmation.ValidatorParams;
import itusolar.historique.dashboard.DashBoardManager;
import itusolar.historique.dashboard.DashBoardManagerSignature;
import itusolar.historique.dashboard.SectionStateEvaluator;
import itusolar.historique.dashboard.difference.ConsumptionComparator;
import itusolar.historique.dashboard.difference.RealConsumptionPreparator;
import itusolar.historique.dashboard.difference.RealConsumptionPreparatorSignature;
import itusolar.historique.dashboard.evolution.EvolutionDash;
import itusolar.historique.dashboard.evolution.EvolutionDashManager;
import itusolar.historique.dashboard.evolution.EvolutionDashManagerSignature;
import itusolar.historique.dashboard.evolution.EvolutionParams;
import itusolar.historique.dashboard.repartition.RepartitionDashManager;
import itusolar.historique.dashboard.section.InverterDash;
import itusolar.historique.dashboard.section.InverterDashManager;
import itusolar.historique.dashboard.section.InverterDashManagerSignature;
import itusolar.historique.filter.BloomFilterSignature;
import itusolar.historique.filter.DoublonException;
import itusolar.historique.filter.FilterParams;
import itusolar.historique.type.TypeManager;
import itusolar.lieu.Section;
import itusolar.lieu.SectionManager;
import itusolar.predict.loader.Loader;
import itusolar.predict.loader.LoaderSignature;
import itusolar.prepare.schedule.dependency.ScheduleDependency;
import itusolar.source.SourceManager;
import itusolar.source.SourceManagerSignature;
import utilitaire.UtilDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "HistoriqueController", urlPatterns = {"/historique/*"})
public class HistoriqueController extends HController {
    HistoryManager manager = new HistoriqueTempNotLoad();
    HistoryValidatorSignature validator;
    InverterDashManagerSignature inverterDashManager = new InverterDashManager();
    EvolutionDashManagerSignature evolutionDashManager;
    EvolutionDashManagerSignature repartitionDashManager;
    EvolutionDashManagerSignature comparatorSignature;
    BloomFilterSignature bloomFilter;
    SourceManagerSignature sourceManager;
    DashBoardManagerSignature dashboard = new DashBoardManager(
            new SectionStateEvaluator(),
            new Loader(null, null, null,null));

    @Override
    public void init() throws ServletException {
        super.init();
        ScheduleDependency dependency = new ScheduleDependency();
        this.evolutionDashManager = new EvolutionDashManager(dependency.getPreparatory(),dependency.getPredictDependency().getLoader());
        this.repartitionDashManager = new RepartitionDashManager(dependency.getPreparatory(),dependency.getPredictDependency().getLoader());
        RealConsumptionPreparatorSignature realConsumption = new RealConsumptionPreparator(dependency.getPredictDependency().getHistoryManager());
        this.comparatorSignature = new ConsumptionComparator(dependency.getPreparatory(),dependency.getPredictDependency().getLoader(), realConsumption);
        this.manager.setLoader(dependency.getPredictDependency().getLoader());
        this.manager.setBloomFilter(dependency.getPredictDependency().getBloomFilter());
        this.sourceManager = new SourceManager(new SectionManager(),new TypeManager());
        LoaderSignature loader = dependency.getPredictDependency().getLoader();
        BloomFilterSignature bloomFilter = dependency.getPredictDependency().getBloomFilter();
        this.validator = new HistoryValidator(loader, bloomFilter);
    }

    @HPost(url = "/inverter/requirements")
    public void inverterRequirements(HttpServletRequest request, HttpServletResponse response, Object params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        InverterFormRequirements requirements = this.sourceManager.inverterRequirements(connection);
        this.writeSuccess(response, null, requirements).close();
        connection.close();
    }

    @HPost(url = "/test/exist")
    public void testInside(HttpServletRequest request, HttpServletResponse response, FilterParams params) throws IOException, DoublonException {
        this.activeJson(response);
        this.bloomFilter.addWhenOk(params);
        this.writeSuccess(response, null, "ok").close();
    }

    @Secretary
    @HPost(url = "/consumption/comparison")
    public void compareConsumption(HttpServletRequest request, HttpServletResponse response, EvolutionParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        EvolutionDash dash = this.comparatorSignature.dashContent(params, connection);
        this.writeSuccess(response, null, dash).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/repartition")
    public void repartition(HttpServletRequest request, HttpServletResponse response, EvolutionParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        EvolutionDash dash = this.repartitionDashManager.dashContent(params,connection);
        this.writeSuccess(response, null, dash).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/evolution")
    public void evolution(HttpServletRequest request, HttpServletResponse response, EvolutionParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        EvolutionDash dash = this.evolutionDashManager.dashContent(params,connection);
        this.writeSuccess(response, null, dash).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/inverter")
    public void inverterState(HttpServletRequest request, HttpServletResponse response, Section section) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        InverterDash dash = this.inverterDashManager.dashContent(section,connection);
        this.writeSuccess(response, null, dash).close();
        connection.close();
    }
    @Secretary
    @HPost(url = "validate")
    public void validate(HttpServletRequest request, HttpServletResponse response, ValidatorParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.validator.validate(params, connection);
        this.writeSuccess(response, null, "").close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/init")
    public void dashboardState(HttpServletRequest request, HttpServletResponse response, Object datas) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null, this.dashboard.dashboard(connection)).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/dashboard")
    public void dashboard(HttpServletRequest req, HttpServletResponse resp,HistoriqueParams params) throws Exception {
        HistEnergyCalc histEnergy = new HistEnergyCalc();
        this.dash(req, resp, params, histEnergy);
    }

    @Secretary
    @HPost(url = "/temporary")
    public void dashboardTemp(HttpServletRequest req, HttpServletResponse resp,HistoriqueParams params) throws Exception {
        HistEnergyCalc histEnergy = new HistTempList();
        this.dash(req, resp, params, histEnergy);
    }

    public void dash(HttpServletRequest request, HttpServletResponse response, HistoriqueParams params, HistEnergyCalc histEnergy) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        DashBoardResult result = new DashBoardResult();
        result.configHistEnergy(this.getManager(),histEnergy,params,connection);
        this.writeSuccess(response, null, result).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/create")
    public void create(HttpServletRequest req, HttpServletResponse resp,HistTemporary params) throws Exception {
        this.loadUser(req);
        this.activeJson(resp);
        Connection connection = (new UtilDB()).GetConn();
        params.setMode("annul");
        this.getManager().create(params, connection);
        this.writeSuccess(resp, null, params).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/multiple")
    public void createMultiple(HttpServletRequest req, HttpServletResponse resp,HistoryParams params) throws Exception {
        this.loadUser(req);
        this.activeJson(resp);
        Connection connection = this.getConnection();
        this.getManager().create(params, connection);
        connection.close();
        this.writeSuccess(resp, null, params).close();
    }

    public HistoryManager getManager() {
        return manager;
    }

    public void setManager(HistoryManager manager) {
        this.manager = manager;
    }
}
