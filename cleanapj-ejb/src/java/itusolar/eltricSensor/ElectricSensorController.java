package itusolar.eltricSensor;

import itusolar.controller.HController;
import itusolar.controller.HPost;
import itusolar.controller.Maintainer;
import itusolar.eltricSensor.call.ElectricCallMethod;
import itusolar.eltricSensor.call.ElectricTester;
import itusolar.eltricSensor.log.ElectricLogger;
import itusolar.eltricSensor.log.mailing.MailAccessor;
import itusolar.eltricSensor.log.mailing.MailAccessorSignature;
import itusolar.eltricSensor.roomLoad.RoomAccessor;
import itusolar.eltricSensor.roomLoad.RoomMethod;
import itusolar.eltricSensor.stat.collector.MultipleCollector;
import itusolar.eltricSensor.stat.evolution.OutageEvolutionSignature;
import itusolar.eltricSensor.stat.evolution.OutagePage;
import itusolar.eltricSensor.stat.evolution.OutageParams;
import itusolar.eltricSensor.stat.evolution.room.RoomOutagePerDayEvolution;
import itusolar.eltricSensor.stat.evolution.room.RoomOutagePerMonthEvolution;
import itusolar.eltricSensor.stat.evolution.sensor.OutagePerDayEvolution;
import itusolar.eltricSensor.stat.evolution.sensor.OutagePerMonthEvolution;
import itusolar.historique.wait.ElectricWaitMethod;
import itusolar.historique.wait.ElectricWaitMethodSignature;
import itusolar.historique.wait.WaitParams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ElectricSensorController", urlPatterns = {"/electric/*"})
public class ElectricSensorController extends HController {
    ElectricManagerSignature callMethod;
    ElectricManagerSignature waitMethod;
    ElectricWaitMethodSignature loader;
    ElectricManagerSignature roomMethod;
    ElectricWaitMethodSignature roomLoader;
    OutageEvolutionSignature outageEvolution;
    OutageEvolutionSignature outageEvolutionPerMonth;
    OutageEvolutionSignature outageRoomEvolution;
    OutageEvolutionSignature outageRoomEvolutionPerMonth;
    MailAccessorSignature mailAccessor;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection connection = this.getConnection();
            this.callMethod = new ElectricCallMethod(new ElectricTester(), new ElectricAccessor());
            ElectricWaitMethod wMethod = new ElectricWaitMethod(new ElectricAccessor());
            this.waitMethod = wMethod;
            this.loader = wMethod;
            RoomAccessor accessor = new RoomAccessor();
            RoomMethod rMethod = new RoomMethod(accessor, accessor);
            this.roomLoader = rMethod;
            this.roomMethod = rMethod;
            this.mailAccessor = new MailAccessor();
            this.roomLoader.start(this.mailAccessor, connection);
            this.outageEvolution = new OutagePerDayEvolution(new MultipleCollector(new ElectricAccessor()));
            this.outageEvolutionPerMonth = new OutagePerMonthEvolution(new MultipleCollector(new ElectricAccessor()));
            this.outageRoomEvolution = new RoomOutagePerDayEvolution(new MultipleCollector(new ElectricAccessor()), accessor);
            this.outageRoomEvolutionPerMonth = new RoomOutagePerMonthEvolution(new MultipleCollector(new ElectricAccessor()), accessor);
//            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Maintainer
    @HPost(url = "/room/evolution/month")
    public void outageRoomMonthEvolution(HttpServletRequest request, HttpServletResponse response, OutageParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        OutagePage pageContent = this.outageRoomEvolutionPerMonth.evolutionPage(params, connection);
        this.writeSuccess(response, null, pageContent).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/room/evolution/day")
    public void outageRoomEvolution(HttpServletRequest request, HttpServletResponse response, OutageParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        OutagePage pageContent = this.outageRoomEvolution.evolutionPage(params, connection);
        this.writeSuccess(response, null, pageContent).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/sensor/evolution/month")
    public void outageMonthEvolution(HttpServletRequest request, HttpServletResponse response, OutageParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        OutagePage pageContent = this.outageEvolutionPerMonth.evolutionPage(params, connection);
        this.writeSuccess(response, null, pageContent).close();
        connection.close();
    }

    @Maintainer
    @HPost(url = "/sensor/evolution/day")
    public void outageEvolution(HttpServletRequest request, HttpServletResponse response, OutageParams params) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        OutagePage pageContent = this.outageEvolution.evolutionPage(params, connection);
        this.writeSuccess(response, null, pageContent).close();
        connection.close();
    }
    @Maintainer
    @HPost(url = "/call/state")
    public void electricCallState(HttpServletRequest request, HttpServletResponse response, Object datas) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        ElectricPage pageContent = this.callMethod.pageContent(connection);
        this.writeSuccess(response, null, pageContent).close();
        connection.close();
    }
    @Maintainer
    @HPost(url = "/wait/state")
    public void electricWaitState(HttpServletRequest request, HttpServletResponse response, Object datas) throws Exception {
        this.activeJson(response);
        ElectricPage pageContent = this.waitMethod.pageContent(null);
        this.writeSuccess(response, null, pageContent).close();
    }

    @Maintainer
    @HPost(url = "/room/state")
    public void roomState(HttpServletRequest request, HttpServletResponse response, Object data) throws Exception {
        this.activeJson(response);
        Connection connection = this.getConnection();
        ElectricPage pageContent = this.roomMethod.pageContent(connection);
        this.writeSuccess(response, null, pageContent).close();
        connection.close();
    }

    @HPost(url = "/state/load")
    public void load(HttpServletRequest request, HttpServletResponse response, WaitParams params) throws Exception {
        this.activeJson(response);
        params = new WaitParams();
        params.setMacAddress(request.getParameter("macAddress"));
//        System.out.println(params.getMacAddress());
        params.configLatency(request.getParameter("latency"));
        this.roomLoader.load(params);
        this.writeSuccess(response, null, "").close();
    }

    @HPost(url = "/log")
    public void log(HttpServletRequest request, HttpServletResponse response, Object data) throws IOException {
        this.activeJson(response);
        ElectricSensor sensor = new ElectricSensor("C1","192.168.51.3","eo:ej:ej:en:en",
                null,1,1,0);
        ElectricLogger logger = new ElectricLogger(sensor,"SensorLogger");
        logger.log();
        this.writeSuccess(response, null, "ok").close();
    }
}
