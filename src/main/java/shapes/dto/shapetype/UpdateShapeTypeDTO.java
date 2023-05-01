package shapes.dto.shapetype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeTypeDTO {
    private int shapeTypeId;
    private String systemName;
    private String name;
}
