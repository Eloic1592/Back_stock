/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

/**
 *
 * @author itu
 */
import com.google.gson.annotations.Expose;
public class ErrorPayload extends Payload {
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorPayload(int status,String message) {
        super(status);
        this.message = message;
    }
}
