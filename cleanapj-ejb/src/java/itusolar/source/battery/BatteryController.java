package itusolar.source.battery;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Maintainer;
import itusolar.controller.Secretary;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "BatteryController", urlPatterns = {"/battery/*"})
public class BatteryController extends HController {
    BatteryAccessorSignature accessor = new BatteryAccessor();

    @HPost( url = "/main/pdf")
    public void pdf(HttpServletRequest request, HttpServletResponse response, Object params) throws Exception {
        Connection connection = this.getConnection();
        this.getExporter().export(new Battery(), request, response, "", connection);
        connection.close();
    }

    @Secretary
    @HPost( url = "/save")
    public void save(HttpServletRequest request, HttpServletResponse response, Battery battery) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.accessor.save(battery, connection);
        this.writeSuccess(response, null, null).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/form/content")
    public void formContent(HttpServletRequest request, HttpServletResponse response, Battery battery) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        BatteryForm form = this.accessor.formContent(battery,connection);
        this.writeSuccess(response, null, form).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/list/content")
    public void listContent(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        BatteryList list = this.accessor.listContent(connection);
        this.writeSuccess(response, null, list).close();
        connection.close();
    }
}
