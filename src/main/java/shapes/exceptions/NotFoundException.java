package shapes.exceptions;

public class NotFoundException extends Throwable {
    public NotFoundException(int id, Class clazz) {
        super(String.format("%s with id = %s is not found", clazz.getSimpleName(), id));
    }
}
