package shapes.exceptions;

public class ShapeValidationException extends RuntimeException {
    public ShapeValidationException(String message) {
        super(message);
    }
}