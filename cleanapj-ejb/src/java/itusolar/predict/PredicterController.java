package itusolar.predict;

import itusolar.controller.HPost;
import itusolar.controller.HController;
import itusolar.controller.Maintainer;
import itusolar.predict.dependency.PredictDependency;
import itusolar.prepare.schedule.Schedule;
import itusolar.simulation.response.SimulPred;
import itusolar.simulation.SimulationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "PredicterController", urlPatterns = {"/simulation/*"})
public class PredicterController extends HController {
    PredicterSignature predicterManager;
    PredictDependency dependency;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dependency = new PredictDependency(PredictDependency.PREDICTORY);
        this.dependency.setOnProd(true);
        this.predicterManager = this.dependency.getPredicter();
    }

    @Maintainer
    @HPost(url = "refresh")
    public void refresh(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception {
        this.dependency.refreshSimulData();
    }

    @Maintainer
    @HPost(url = "direct")
    public void predict(HttpServletRequest request, HttpServletResponse response,PredictionParameter parameter) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        SimulPred result = this.predicterManager.predict(parameter,connection);
        this.writeSuccess(response, null, result).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "utils")
    public void utils(HttpServletRequest request, HttpServletResponse response, Schedule params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        SimulationUtils utils = new SimulationUtils(params,this.dependency.getAccessor(),connection);
        this.writeSuccess(response,null,utils).close();
        connection.close();
    }
}
