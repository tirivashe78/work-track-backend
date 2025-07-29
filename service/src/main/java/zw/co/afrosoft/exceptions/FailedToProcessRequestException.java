package zw.co.afrosoft.exceptions;

public class FailedToProcessRequestException extends RuntimeException {
    public FailedToProcessRequestException(String message) {
        super(message);
    }
}
