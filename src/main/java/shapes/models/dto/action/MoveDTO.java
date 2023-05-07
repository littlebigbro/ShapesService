package shapes.models.dto.action;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.PointFieldNames;
import shapes.models.dto.fieldNames.ShapeFieldNames;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoveDTO implements ShapeFieldNames, PointFieldNames {

    @NotNull(message = "Атрибут shapeId (" + SHAPE_ID + ") обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_ID, required = true)
    private Long shapeId;

    @NotNull(message = "Атрибут x (" + X_NAME + ") обязателен для заполнения")
    @ApiModelProperty(value = X_NAME, required = true)
    private Double x;

    @NotNull(message = "Атрибут y (" + Y_NAME + ") обязателен для заполнения")
    @ApiModelProperty(value = Y_NAME, required = true)
    private Double y;
}
