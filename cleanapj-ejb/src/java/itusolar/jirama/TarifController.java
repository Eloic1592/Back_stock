package itusolar.jirama;

import itusolar.controller.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "TarifController", urlPatterns = {"/tarif/*"})
public class TarifController extends HController {

    HTarifManager manager = new TarifManager();
    HTaxeManager taxeManager = new TaxeManager();

    @Maintainer
    @HPost(url = "/taxe/utils")
    public void taxeUtils(HttpServletRequest request, HttpServletResponse response,TaxeRequest params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        TaxeForm form = this.taxeManager.formContent(params,connection);
        this.write(response, null, form).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/addTaxe")
    public void addTaxes(HttpServletRequest request, HttpServletResponse response, Taxe taxe) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.taxeManager.addTaxe(taxe, connection);
        this.write(response,null,null).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/form")
    public void formContent(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.write(response, null, this.getManager().formContent(connection)).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/save")
    public void save(HttpServletRequest request, HttpServletResponse response, Tarif tarif) throws  Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.getManager().create(tarif,connection);
        this.write(response, null, null).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/pages")
    public void pageTarif(HttpServletRequest req, HttpServletResponse resp, Object data) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        PageTarifResponse result = this.getManager().pageTarifContent(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    public HTarifManager getManager() {
        return manager;
    }

    public void setManager(HTarifManager manager) {
        this.manager = manager;
    }
}
