package itusolar.auth;

public class PermissionDeniedException extends Exception{
    public PermissionDeniedException() {
        super("Authorisation non accordée");
    }
}
