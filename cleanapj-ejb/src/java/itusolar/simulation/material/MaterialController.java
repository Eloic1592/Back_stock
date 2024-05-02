package itusolar.simulation.material;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Maintainer;
import itusolar.controller.Secretary;

import javax.management.ObjectName;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "MaterialController", urlPatterns = {"/material/*"})
public class MaterialController extends HController {
    MaterialManagerSignature materialManager = new MaterialManager();

    @Secretary
    @HPost(url = "/save")
    public void save(HttpServletRequest request, HttpServletResponse response, Material material) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.materialManager.save(material, connection);
        this.writeSuccess(response, null, material).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/form")
    public void formContent(HttpServletRequest request, HttpServletResponse response, Material datas) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        MaterialForm form = this.materialManager.formContent(datas,connection);
        this.writeSuccess(response, null, form).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/all")
    public void listContent(HttpServletRequest request, HttpServletResponse response,Object datas) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        MaterialList list = this.materialManager.listContent(connection);
        this.writeSuccess(response,null,list).close();
        connection.close();
    }
}
