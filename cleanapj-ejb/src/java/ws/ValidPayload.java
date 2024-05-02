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

public class ValidPayload extends Payload {
    @Expose
    Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ValidPayload(int code,Object data) {
        this.setStatus(code);
        this.setData( data);
    }
    
    
}
