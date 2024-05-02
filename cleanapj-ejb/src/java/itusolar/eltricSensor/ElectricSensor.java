package itusolar.eltricSensor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itusolar.prepare.MappedInteger;
import itusolar.simulation.UtilsSignature;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElectricSensor extends MappedInteger implements UtilsSignature {
    String title, ipaddress,macAddress;
    @JsonIgnore
    String fileHandler;
    int roomId, lineId, position;

    public ElectricSensor() {
        this.setNomTable("electricSensor");
    }

    public ElectricSensor(String title, String ipaddress, String macAddress, String fileHandler, int roomId, int lineId, int position) {
        this();
        this.setTitle(title);
        this.setIpaddress(ipaddress);
        this.setMacAddress(macAddress);
        this.setFileHandler(fileHandler);
        this.setRoomId(roomId);
        this.setLineId(lineId);
        this.setPosition(position);
    }

    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("", "GETSeqElectricSensor");
        this.setId(Integer.parseInt(makePK(c)));
    }

    public String getFileHandler() throws IOException {
        return this.getFileHandler(LocalDate.now());
    }

    public String getFileHandler(LocalDate date) throws IOException {
//        if (this.fileHandler == null) {
        date = date == null ? LocalDate.now() : date;
        String folderPath = "sensor_logs";
        String path = String.format("%s/%s_%s_%s_%s.log",folderPath,this.formatTitle(),date.getYear(),date.getMonth().getValue(),date.getDayOfMonth());
        this.setFileHandler(path);
        File file = new File(folderPath);
        file.mkdir();
        file = new File(path);
        file.createNewFile();
//        }
        return this.fileHandler;
    }

    public String formatTitle() {
        String response = this.getTitle().toLowerCase();
        response = response.replaceAll(" ", "_");
        response = response.replaceAll(",", "_");
        response = response.replaceAll("'", "_");
        response = response.replaceAll("-", "_");
        return response;
    }

    public void setFileHandler(String fileHandler) {
        this.fileHandler = fileHandler;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ElectricSensor{" +
                "title='" + title + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", roomId=" + roomId +
                ", lineId=" + lineId +
                ", position=" + position +
                '}';
    }

    @Override
    public String getLabel() {
        return this.getTitle();
    }
}
