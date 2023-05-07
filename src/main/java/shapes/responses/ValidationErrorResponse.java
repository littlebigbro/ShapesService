package shapes.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class ValidationErrorResponse {
    private LocalDateTime timestamp;
    private List<Map<String, String>> errorMessages;

    public ValidationErrorResponse(List<FieldError> errors) {
        this.timestamp = LocalDateTime.now();
        this.errorMessages = errors.stream()
                .map(error -> {
                    Map<String, String> m = new HashMap<>();
                    String defaultMessage = Objects.toString(error.getDefaultMessage(), "%s");
                    m.put("errormessage", String.format(defaultMessage, error.getField()));
                    return m;
                })
                .collect(Collectors.toList());
    }
}