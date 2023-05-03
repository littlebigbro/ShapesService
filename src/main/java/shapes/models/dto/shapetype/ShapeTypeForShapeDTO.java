package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeTypeForShapeDTO {

    @ApiModelProperty(value = "id типа фигуры в базе данных", required = true)
    private int shapeTypeId;

    @NotNull
    @ApiModelProperty(value = "Системное наименование типа фигуры", required = true)
    private String systemName;

    @NotNull
    @ApiModelProperty(value = "Наименование типа фигуры", required = true)
    private String name;
}
