package itusolar.eltricSensor;

import itusolar.eltricSensor.client.communication.ClientConnectorSignature;
import itusolar.eltricSensor.client.communication.SocketConnection;
import itusolar.eltricSensor.roomLoad.RoomAccessor;
import itusolar.eltricSensor.roomLoad.RoomMethod;
import itusolar.prepare.HServiceManager;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/real-time/connection/{token}" )
public class ElectricEndPoint extends HServiceManager {

    Map<String, ClientConnectorSignature> clients = new HashMap<>();
    ElectricManagerSignature roomMethod;

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        this.init();
        SocketConnection client = new SocketConnection();
        client.setSession(session);
        this.getClients().put(session.getId(), client);
        this.logConnected(client);
    }

    public void init() {
        if (this.getRoomMethod() == null) {
            RoomAccessor accessor = new RoomAccessor();
            RoomMethod method = new RoomMethod(accessor, accessor);
            this.setRoomMethod(method);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        ClientConnectorSignature client = getClients().get(session.getId());
        this.logDisConnected(client);
        this.getClients().remove(session.getId());
    }

    public void logDisConnected(ClientConnectorSignature client) {
        Logger logger = Logger.getLogger("Client Logout");
        logger.log(Level.SEVERE, ((SocketConnection)client).getSession().getId());
    }

    public ElectricManagerSignature getRoomMethod() {
        return roomMethod;
    }

    public void setRoomMethod(ElectricManagerSignature roomMethod) {
        this.roomMethod = roomMethod;
    }

    public void logConnected(ClientConnectorSignature client) {
        Logger logger = Logger.getLogger("Client Connected");
        logger.log(Level.INFO, ((SocketConnection)client).getSession().getId());

    }

    public Map<String, ClientConnectorSignature> getClients() {
        return clients;
    }

    public void setClients(Map<String, ClientConnectorSignature> clients) {
        this.clients = clients;
    }
}
