package inventorymanagement.dashboard.dashboard;


import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "DashboardController", urlPatterns = {"/dashboard/*"})
public class DashboardController extends HController {

    DashboardManager dashboardManager=new DashboardManager();

    @HPost(url="/etatstockannee")
    public void etatstockannee(HttpServletRequest request, HttpServletResponse response,DashboardParams dashboardParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.dashboardManager.etatdestockparannee(dashboardParams.getAnnee(),connection)).close();
        connection.close();
    }

    @HPost(url="/etatdetailstockannee")
    public void etatdetailstockannee(HttpServletRequest request, HttpServletResponse response,DashboardParams dashboardParams) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.dashboardManager.etatdetaildestockparannee(dashboardParams.getAnnee(), dashboardParams.getMois(), connection)).close();
        connection.close();
    }



}
