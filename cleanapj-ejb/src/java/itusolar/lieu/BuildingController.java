package itusolar.lieu;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Maintainer;
import itusolar.controller.Secretary;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "BuildingController", urlPatterns = {"/building/*"})
public class BuildingController extends HController {
    BuildingManagerSignature buildingManager = new BuildingManager(new SectionManager());

    @Secretary
    @HPost(url = "/create")
    public void create(HttpServletRequest request, HttpServletResponse response,Building building) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.buildingManager.create(building, connection);
        this.writeSuccess(response, null, "ok").close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/all")
    public void getAll(HttpServletRequest request, HttpServletResponse response, Object datas) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        BuildingList list = this.buildingManager.contentList(connection);
        this.writeSuccess(response, null,list).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/utils")
    public void formUtils(HttpServletRequest request, HttpServletResponse response, Building building) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        BuildingForm form = this.buildingManager.contentForm(building,connection);
        this.writeSuccess(response, null,form).close();
        connection.close();
    }

}
