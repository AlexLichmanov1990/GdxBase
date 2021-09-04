package engine.exceptions;

public class IllegalResourceException extends Exception {
    private static final String message = "Can't add the resource. The resource is already added.";

    public IllegalResourceException() {
        super(message);
    }
}
