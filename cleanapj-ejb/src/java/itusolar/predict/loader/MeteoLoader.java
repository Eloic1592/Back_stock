package itusolar.predict.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import itusolar.lieu.BuildingManagerSignature;
import itusolar.lieu.SectionManagerSignature;
import itusolar.simulation.DateConcern;
import itusolar.simulation.material.MaterialManagerSignature;
import itusolar.source.SectionCapacityManagerSignature;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MeteoLoader extends Loader{
    String urlMeteo;
    HttpURLConnection con;
    URL url;
    Map<String, String> parameters = new HashMap<>();
    DateConcern[] dateConcerns;
    MeteoData response;
    public static final String longitude = "47.53276528835945";
    public static final String longitudeLabel = "longitude";
    public static final String latitude = "-18.98568035435629";
    public static final String latitudeLabel = "latitude";
    public static final String hourly = "temperature_2m";
    public static final String hourlyLabel = "hourly";
    public static final String daily = "temperature_2m_max";
    public static final String dailyLabel = "daily";
    public static final String timezone = "auto";
    public static final String timezoneLabel = "timezone";
    public static final String startLabel = "start_date";
    public static final String endLabel = "end_date";

    public MeteoLoader(BuildingManagerSignature lieuManager, MaterialManagerSignature appareilManager, SectionCapacityManagerSignature sectionCapacityManager, SectionManagerSignature sectionManager) {
        super(lieuManager, appareilManager, sectionCapacityManager, sectionManager);
        this.setUrlMeteo( "https://api.open-meteo.com/v1/forecast");
    }
    @Override
    public void loadTemperature(DateConcern[] dates, Connection connection) throws IOException {
        System.out.println("Meteo called");
        this.setDateConcerns(dates);
        this.initAndClose();
        this.loadTemp();
    }

    public void initAndClose() throws IOException {
        this.init();
        this.getCon().disconnect();
    }

    public void loadTemp() {
        for (DateConcern date : this.getDateConcerns()) {
            date.setTemperature(this.search(date));
        }
    }

    public void init() throws IOException {
        this.initParameters();
        this.initConnection();
        this.getContent();
    }

    public double search(DateConcern dateConcern) {
        return this.getResponse().getHourly().search(dateConcern.getTimestamp());
    }

    public void getContent() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.getCon().getInputStream()));
        String inputLine = "";
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            content.append(inputLine);
        ObjectMapper mapper = new ObjectMapper();
        MeteoData data = mapper.readValue(content.toString(), MeteoData.class);
        this.setResponse(data);
        in.close();
    }

    public void initParameters() {
        this.addParameter(MeteoLoader.latitudeLabel,MeteoLoader.latitude);
        this.addParameter(MeteoLoader.longitudeLabel,MeteoLoader.longitude);
        this.addParameter(MeteoLoader.hourlyLabel, MeteoLoader.hourly);
        this.addParameter(MeteoLoader.dailyLabel, MeteoLoader.daily);
        this.addParameter(MeteoLoader.timezoneLabel,MeteoLoader.timezone);
        this.addParameter(MeteoLoader.startLabel, this.getStartDate());
        this.addParameter(MeteoLoader.endLabel, this.getEndDate());
    }

    private String getEndDate() {
        DateConcern startDate = this.getDateConcerns()[this.getDateConcerns().length-1];
        return this.format(startDate.getTimestamp());
    }

    public String getStartDate() {
        DateConcern startDate = this.getDateConcerns()[0];
        return this.format(startDate.getTimestamp());
    }

    public String format(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

    public void addParameter(String name, String value) {
        this.getParameters().put(name, value);
    }

    public void initConnection() throws IOException {
        this.initUrl();
        this.setCon((HttpURLConnection) this.getUrl().openConnection());
        this.getCon().setRequestMethod("GET");
        this.getCon().setConnectTimeout(10000);
        this.getCon().setDoOutput(true);
    }

    public void initUrl() throws MalformedURLException, UnsupportedEncodingException {
        this.setUrl(new URL(this.prepareUrl()));
    }

    public String prepareUrl() throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder(this.getUrlMeteo());
        url.append("?");
        boolean verif = false;
        String encoding = "UTF-8";
        for (Map.Entry<String, String> entry : this.getParameters().entrySet()) {
            url.append(verif ? "&" : "");
            verif = true;
            url.append(URLEncoder.encode(entry.getKey(), encoding));
            url.append("=");
            url.append(URLEncoder.encode(entry.getValue(), encoding));
        }
        String response = url.toString();
        System.out.println(response);
        return response;
    }

    public MeteoData getResponse() {
        return response;
    }

    public void setResponse(MeteoData response) {
        this.response = response;
    }

    public HttpURLConnection getCon() {
        return con;
    }

    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public String getUrlMeteo() {
        return urlMeteo;
    }

    public void setUrlMeteo(String urlMeteo) {
        this.urlMeteo = urlMeteo;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public DateConcern[] getDateConcerns() {
        return dateConcerns;
    }

    public void setDateConcerns(DateConcern[] dateConcerns) {
        this.dateConcerns = dateConcerns;
    }
}
