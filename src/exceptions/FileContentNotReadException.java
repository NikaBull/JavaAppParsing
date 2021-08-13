package nikita.parsit.papki.com.exceptions;

public class FileContentNotReadException extends RuntimeException {
    public FileContentNotReadException(Throwable cause) {
        super(cause);
    }

    public FileContentNotReadException(String message) {
        super(message);
    }
}
