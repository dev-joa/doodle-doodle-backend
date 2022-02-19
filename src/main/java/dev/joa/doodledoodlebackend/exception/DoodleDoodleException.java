package dev.joa.doodledoodlebackend.exception;

public class DoodleDoodleException extends RuntimeException {

    public DoodleDoodleException() {
        super();
    }

    public DoodleDoodleException(String message) {
        super(message);
    }

    public DoodleDoodleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoodleDoodleException(Throwable cause) {
        super(cause);
    }
}
