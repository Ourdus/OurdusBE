package ourdus.ourdusspring.common;

public class ForbiddenException extends Exception{
    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
