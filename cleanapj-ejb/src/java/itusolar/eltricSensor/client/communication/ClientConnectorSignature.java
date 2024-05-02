package itusolar.eltricSensor.client.communication;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ClientConnectorSignature {
    public void send(HMessage message) throws IOException;
}
