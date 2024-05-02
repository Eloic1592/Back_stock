package itusolar.auth;

import historique.MapUtilisateur;
import historique.UtilisateurUtil;
import itusolar.controller.HController;
import itusolar.controller.HPost;
import user.UserEJB;
import user.UserEJBClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "LoginController", urlPatterns = {"/authentification/*"})
public class LoginController extends HController {
    LoginManager loginManager = new LoginManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @HPost(url = "/login")
    public void login(HttpServletRequest req, HttpServletResponse resp, itusolar.auth.UserParam param) throws ServletException, IOException, SQLException {
        this.activeJson(resp);
        Connection connection = null;
        try {
            connection = this.getConnection();
            LoginResult login = this.loginManager.login(param, connection);
            this.writeSuccess(resp, null, login).close();
        } catch (Exception e) {
            e.printStackTrace();
            this.writeError(resp,null, "Login error").close();
        } finally {
            if (connection != null) connection.close();
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper userMapper = new ObjectMapper();
//        PrintWriter writer = resp.getWriter();
//        try {
//            UserParam param =userMapper.readValue(req.getReader(),UserParam.class);
//            writer.println(param);
//        } catch (Exception e) {
//            e.printStackTrace();
//            writer.println("error");
//        }
//        writer.close();
////        params.forEach((key,value) -> {
////            System.out.println(key);
////            System.out.println(value);
////        });
////        UserEJB u = UserEJBClient.lookupUserEJBBeanLocal();
////        try {
////            u.testLogin(userName,passWord,interim,service);
////            req.setAttribute("data",new SuccessResponse(u));
////        } catch (Exception e) {
////            req.setAttribute("data",new Error("Login error"));
////        }
//
//    }
}
