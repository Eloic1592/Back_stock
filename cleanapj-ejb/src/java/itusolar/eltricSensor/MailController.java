package itusolar.eltricSensor;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Secretary;
import itusolar.eltricSensor.log.mailing.MailAccessor;
import itusolar.eltricSensor.log.mailing.MailAccessorSignature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "MailController", urlPatterns = {"/mail/*"})
public class MailController extends HController {
    MailAccessorSignature mailAccessor;

    @Override
    public void init() throws ServletException {
        super.init();
        this.mailAccessor = new MailAccessor();
    }

    @Secretary
    @HPost(url = "/all")
    public void listContent(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception {
        Connection connection = this.jsonAndConnection(response);
        MailListContent content = this.mailAccessor.listContent(connection);
        this.write(response, null, content).close();
        connection.close();
    }

    @Secretary
    @HPost(url = "/create")
    public void createMail(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception {

    }
}
