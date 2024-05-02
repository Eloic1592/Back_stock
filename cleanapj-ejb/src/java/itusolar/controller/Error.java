package itusolar.controller;

public class Error {
    String message;

    public Error() {
    }

    public Error(String message) {
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
