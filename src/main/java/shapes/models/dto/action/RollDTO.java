package shapes.models.dto.action;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.ShapeFieldNames;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollDTO implements ShapeFieldNames {

    @NotNull(message = "Атрибут shapeId (" + SHAPE_ID + ") обязателен для заполнения")
    @ApiModelProperty(value = "id фигуры в базе данных", required = true)
    private Long shapeId;

    @NotNull(message = "Атрибут angle (угол поворота) обязателен для заполнения")
    @ApiModelProperty(value = "угол поворота", required = true)
    private Double angle;
}
