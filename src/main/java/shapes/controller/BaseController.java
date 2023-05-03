package shapes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shapes.exceptions.NotFoundException;
import shapes.responses.ErrorResponse;

import java.time.LocalDateTime;

public interface BaseController {
    @ExceptionHandler(NotFoundException.class)
    default ResponseEntity<ErrorResponse> handleException(NotFoundException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
