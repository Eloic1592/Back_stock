package itusolar.prepare;

public class NoSuchFileException extends Exception{
    public NoSuchFileException() {
        super("No such file or directory");
    }
}
