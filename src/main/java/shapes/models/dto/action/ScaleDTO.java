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
public class ScaleDTO implements ShapeFieldNames {

    @NotNull(message = "Атрибут shapeId (" + SHAPE_ID + ") обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_ID, required = true)
    private Long shapeId;

    @NotNull(message = "Атрибут scaleFactor (коэффициент масштабирования) обязателен для заполнения")
    @ApiModelProperty(value = "коэффициент масштабирования", required = true)
    private Double scaleFactor;
}
