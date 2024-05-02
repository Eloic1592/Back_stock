package ws;
import com.google.gson.annotations.Expose;
public class Payload {
    @Expose
    private int status;

    public Payload(int status) {
        this.status = status;
    }

    public Payload() {
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
