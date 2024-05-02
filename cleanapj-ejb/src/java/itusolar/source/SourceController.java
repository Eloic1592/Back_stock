package itusolar.source;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Maintainer;
import itusolar.controller.Secretary;
import itusolar.historique.type.TypeManager;
import itusolar.lieu.SectionManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "SourceController", urlPatterns = {"/source/*"})
public class SourceController extends HController {
    SourceManagerSignature manager = new SourceManager(new SectionManager(),new TypeManager());

    @Secretary
    @HPost(url="/save")
    public void save(HttpServletRequest request, HttpServletResponse response, Source source) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.getManager().save(source,connection);
        this.writeSuccess(response, null, null).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "form/content")
    public void formContent(HttpServletRequest request, HttpServletResponse response,Object params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        SourceForm formContent = this.getManager().formContent(connection);
        this.writeSuccess(response,null,formContent).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "pages")
    public void getAll(HttpServletRequest req, HttpServletResponse resp,Object params) throws Exception {
        this.activeJson(resp);
        Connection connection = this.getConnection();
        PageSourceResult result = this.getManager().pageSourceContent(connection);
        this.writeSuccess(resp, null, result).close();
        connection.close();
    }

    public SourceManagerSignature getManager() {
        return manager;
    }

    public void setManager(SourceManagerSignature manager) {
        this.manager = manager;
    }
}
