package inventorymanagement.login;

import itusolar.controller.HController;
import itusolar.controller.HPost;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "LoginController",urlPatterns = {"/login/*"})
public class LoginController extends HController {
    LoginManager loginManager=new LoginManager();

    @HPost(url="/login")
    public void getOne(HttpServletRequest request, HttpServletResponse response, UserParam userParam) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        this.writeSuccess(response, null,this.loginManager.getOne(userParam,connection)).close();
        connection.close();
    }
}
