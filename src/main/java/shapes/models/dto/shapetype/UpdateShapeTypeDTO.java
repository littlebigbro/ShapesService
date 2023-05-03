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
public class UpdateShapeTypeDTO {
    private int shapeTypeId;
    private LocalDateTime created;
    private String systemName;
    private String name;
}
