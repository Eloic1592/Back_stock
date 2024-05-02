package itusolar.prepare.schedule;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Maintainer;
import itusolar.controller.Secretary;
import itusolar.prepare.schedule.dependency.ScheduleDependency;
import itusolar.prepare.schedule.devide.Divider;
import itusolar.prepare.schedule.devide.DividerParams;
import itusolar.prepare.schedule.devide.DividerSignature;
import itusolar.prepare.schedule.predict.SchedulePage;
import itusolar.prepare.schedule.predict.ScheduleParams;
import itusolar.prepare.schedule.predict.SchedulePredictorSignature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ScheduleController", urlPatterns = {"/schedule/*"})
public class ScheduleController extends HController {
    ScheduleAccessorSignature accessor;
    SchedulePredictorSignature predictor;
    DividerSignature divider;
    itusolar.prepare.schedule.dependency.ScheduleDependency dependency;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dependency = new ScheduleDependency();
        this.accessor = this.dependency.getAccessor();
        this.predictor = this.dependency.getPredictor();
        this.divider = new Divider();
    }

    @Maintainer
    @HPost(url = "divide")
    public void divide(HttpServletRequest request, HttpServletResponse response, DividerParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.divider.divide(params, connection);
        this.writeSuccess(response, null, "").close();
        connection.close();
    }

    @Secretary
    @HPost(url = "start")
    public void activeIt(HttpServletRequest request, HttpServletResponse response, Schedule schedule) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.accessor.active(schedule, connection);
        this.writeSuccess(response, null, schedule).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/all")
    public void getAll(HttpServletRequest request, HttpServletResponse response,Object data) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        ScheduleList list = this.accessor.getListContents(connection);
        this.write(response,null,list).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/create")
    public void create(HttpServletRequest request,HttpServletResponse response,ScheduleCreationParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        params.setDataIgnore(this.dependency.getPredictDependency().getDataIgnore());
        this.accessor.create(params,connection);
        this.write(response,null,null).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/calendar")
    public void predict(HttpServletRequest request, HttpServletResponse response, ScheduleParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        SchedulePage page = this.predictor.predict(params,connection);
        this.write(response,null,page).close();
        connection.close();
    }

}
