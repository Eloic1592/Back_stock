package itusolar.historique.wait;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WaitParams {
    String macAddress;
    long latency;

    public WaitParams() {
    }

    public WaitParams(String macAddress, long latency) {
        this.setMacAddress(macAddress);
        this.configLatency(latency);
    }

    @Override
    public String toString() {
        return "WaitParams{" +
                "macAddress='" + macAddress + '\'' +
                ", latency=" + latency +
                '}';
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public long getLatency() {
        return latency;
    }
    public void configLatency(String latency) {
        if (latency != null) {
            this.configLatency(Long.parseLong(latency));
        }
    }
    public void configLatency(long latency) {
        this.latency = latency;
    }
}
