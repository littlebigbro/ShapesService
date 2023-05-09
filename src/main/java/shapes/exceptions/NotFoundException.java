package shapes.exceptions;

public class NotFoundException extends RuntimeException {
    public static final String SHAPE_NOT_FOUND_MSG = "Фигура с id = %s не найдена";
    public static final String SHAPE_TYPE_NOT_FOUND_MSG = "Тип фигуры с id = %s не найден";

    public NotFoundException(String message, Long id) {
        super(String.format(message, id));
    }
}