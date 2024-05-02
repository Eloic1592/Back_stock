package itusolar.eltricSensor.client.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import itusolar.prepare.HServiceManager;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;

public class SocketConnection extends HServiceManager implements ClientConnectorSignature {
    Session session;

    @Override
    public void send(HMessage data) throws IOException {
        String message = data.toJSON();
        this.getSession().getBasicRemote().sendText(message);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
