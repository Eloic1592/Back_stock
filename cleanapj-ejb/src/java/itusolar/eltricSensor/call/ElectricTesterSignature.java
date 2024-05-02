package itusolar.eltricSensor.call;

import itusolar.eltricSensor.ElectricState;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface ElectricTesterSignature {
    public void evaluate(ElectricState[] states) throws IOException;
}
