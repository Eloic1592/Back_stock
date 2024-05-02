package itusolar.auth;

public class NoUserForTokenException extends Exception{
    public NoUserForTokenException() {
        super("Add first an user instance before generating token.");
    }
}
