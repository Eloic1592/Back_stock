package inventorymanagement.etudiant;

import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "EtudiantController",urlPatterns = {"/etudiant/*"})
public class EtudiantController extends HController {

    EtudiantManager etudiantManager=new EtudiantManager();

    @HPost(url="/listetudiant")
    public void getall(HttpServletRequest request, HttpServletResponse response, ListeEtudiant o) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.etudiantManager.getall(connection)).close();
        connection.close();
    }

}
