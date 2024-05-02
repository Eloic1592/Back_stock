package itusolar.eltricSensor.call;

import itusolar.eltricSensor.ElectricState;
import itusolar.prepare.HServiceManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ElectricTestExecutor extends HServiceManager implements Runnable {
    String urlSensor;
    HttpURLConnection con;
    URL url;
    ElectricState state;

    public ElectricTestExecutor(ElectricState state) {
        this.setUrlSensor(state.getIpaddress());
        this.setState(state);
    }

    @Override
    public void run() {
        this.setUrlSensor(this.getState().getIpaddress());
        int responseCode =0;
        try {
            this.init();
            responseCode = this.getCon().getResponseCode();
        } catch (Exception ex) {
            responseCode = 404;
        }
        this.getState().setOpen(responseCode < 400);
        this.getCon().disconnect();
    }

    public void init() throws IOException {
        this.initUrl();
        this.setCon((HttpURLConnection) this.getUrl().openConnection());
        this.getCon().setRequestMethod("GET");
        this.getCon().setConnectTimeout(1000);
        this.getCon().setDoOutput(true);
    }

    public void initUrl() throws MalformedURLException {
        this.setUrl(new URL(this.getUrlSensor()));
    }

    public ElectricState getState() {
        return state;
    }

    public void setState(ElectricState state) {
        this.state = state;
    }

    public HttpURLConnection getCon() {
        return con;
    }

    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getUrlSensor() {
        return urlSensor;
    }

    public void setUrlSensor(String urlSensor) {
        this.urlSensor = "http://"+urlSensor;
    }

}
