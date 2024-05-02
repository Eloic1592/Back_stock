package itusolar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import itusolar.auth.LoginController;
import itusolar.auth.LoginManager;
import itusolar.auth.UserParam;
import itusolar.controller.export.*;
import itusolar.utils.HUtils;
import org.apache.http.HttpStatus;
import user.UserEJB;
import user.UserEJBClient;
import utilitaire.UtilDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * The type HController was created for those who want to make a RestFull Controller like in Spring Boot.
 * You have to put an annotation {@link HPost} for all post methods. Each post method should have 3 parameters
 * as the example on this @see {@link LoginController#login(HttpServletRequest, HttpServletResponse, UserParam)}
 */
public class HController extends HttpServlet {
    UserEJB user;
    LoginManager loginManager = new LoginManager();
    ExportGeneratorSignature exporter;
    Map<String, HUrlMapping> methods = new HashMap<>();

    @Override
    public void init() throws ServletException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC/GMT+3"));
        XMLGeneratorSignature xml = new XMLGenerator();
        XMLGeneratorSignature csvGenerator = new CSVGenerator();
        XLSGeneratorSignature xlsGenerator = new XLSGenerator();
        PDFGeneratorSignature pdfGenerator = new PDFGenerator();
        this.setExporter(new ExportGenerator(xml, csvGenerator, xlsGenerator, pdfGenerator));
    }

    public Connection jsonAndConnection(HttpServletResponse response) {
        this.activeJson(response);
        return this.getConnection();
    }

    public ExportGeneratorSignature getExporter() {
        return exporter;
    }

    public void setExporter(ExportGeneratorSignature exporter) {
        this.exporter = exporter;
    }

    public void activeJson(HttpServletResponse resp) {
        resp.setContentType("application/json; charset=UTF-8");
    }
    public Connection getConnection() {
        return (new UtilDB()).GetConn();
    }
    public void loadUser(HttpServletRequest request) throws Exception {
        if (this.getUser() == null)
            this.setUser(this.getUserSession(request));
    }

    private UserEJB getUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object data = null;
        if ((data = session.getAttribute("u")) != null) {
            return (UserEJB) data;
        }
        UserEJB u = UserEJBClient.lookupUserEJBBeanLocal();
        session.setAttribute("u",u);
        return u;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.allow(resp);
    }

    public void allow(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
        resp.addHeader("Access-Control-Allow-Headers","content-type");
        resp.addHeader("Access-Control-Allow-Headers","tokenvalue");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.allow(resp);
        HUrlMapping method = null;
        if ((method = this.getMethods().get(req.getRequestURI())) != null) {
            try {
                this.execute(req,resp, method.getMethod(), method.getPost());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            this.executeFirst(req, resp);
        }
    }

    public void executeFirst(HttpServletRequest request, HttpServletResponse response ) {
        ArrayList<Method> methods = new ArrayList<>();
        itusolar.utils.HUtils.addMethods(this.getClass(), methods);
//          resp.setContentType("");
        methods.forEach(method -> {
            HPost post = null;
            if ((post = method.getAnnotation(HPost.class)) != null)  {
                try {
                    this.execute(request,response,method,post);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public PrintWriter writeError(HttpServletResponse resp, PrintWriter writer, Object data) throws IOException {
        resp.setStatus(HttpStatus.SC_NOT_FOUND);
        return this.write(resp,writer,data);
    }

    protected PrintWriter writeSuccess(HttpServletResponse resp, PrintWriter writer, Object data) throws IOException {
        resp.setStatus(HttpStatus.SC_OK);
        return this.write(resp,writer,data);
    }

    public PrintWriter write(HttpServletResponse resp, PrintWriter writer, Object data) throws IOException {
        writer = (writer != null) ? writer : resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String dataStr = mapper.writeValueAsString(data);
        writer.println(dataStr);
        return writer;
    }

    public void execute(HttpServletRequest req,HttpServletResponse resp,Method method,HPost post) throws Exception {
        String uri = req.getRequestURI();
        if (uri.contains(post.url())) {
            HUrlMapping urlMapping = new HUrlMapping(method, post);
            this.getMethods().put(uri, urlMapping);
            this.execute(req, resp, method);
        }
    }

    public void execute(HttpServletRequest req,HttpServletResponse resp,Method method) throws Exception {
        try {
            this.checkLogin(req, method);
            Object data = this.fromJson(req, HUtils.getThirdParmType(method));
            method.invoke(this,req,resp,data);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void checkLogin(HttpServletRequest request, Method method) throws Exception {
        Admin admin = method.getAnnotation(Admin.class);
        int value = (admin != null) ? admin.value() : -1;
//        System.out.println(admin);
        Secretary secretary = method.getAnnotation(Secretary.class);
        value = (value < 0 && secretary != null) ? secretary.value() : value;
        Maintainer maintainer = method.getAnnotation(Maintainer.class);
        value = (value < 0 && maintainer != null) ? maintainer.value() : value;
        if (value < 0)
            return;
        UserParam param = new UserParam();
        param.setToken(request.getHeader("tokenvalue"));
        Connection connection = this.getConnection();
        this.loginManager.checkLogin(param,value, connection);
        connection.close();
    }

    public Object fromJson(HttpServletRequest req,Class<?> clazz) {
//        System.out.println(clazz.getName());
        ObjectMapper userMapper = new ObjectMapper();
        try {
            BufferedReader reader = req.getReader();
            StringBuilder content = new StringBuilder();
            String temp = "";
            while (( temp = reader.readLine()) != null)
                content.append(temp);
            return userMapper.readValue(content.toString(),clazz);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Map<String, HUrlMapping> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, HUrlMapping> methods) {
        this.methods = methods;
    }

    public UserEJB getUser() {
        return user;
    }

    public void setUser(UserEJB user) {
        this.user = user;
    }
}
