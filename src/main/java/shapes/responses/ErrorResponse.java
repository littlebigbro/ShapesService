package shapes.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
}
