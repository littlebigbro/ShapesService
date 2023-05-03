package shapes.models.dto.shapetype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeTypeDTO {
    private int shapeTypeId;
    private String systemName;
    private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
}
