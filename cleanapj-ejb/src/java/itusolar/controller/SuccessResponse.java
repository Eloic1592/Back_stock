package itusolar.controller;

public class SuccessResponse {
    Object data;

    public SuccessResponse() {
    }

    public SuccessResponse(Object data) {
        this.setData(data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
