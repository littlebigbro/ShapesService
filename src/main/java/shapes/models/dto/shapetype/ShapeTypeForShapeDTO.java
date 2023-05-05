package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeTypeForShapeDTO implements ShapeTypeFieldNames {

    @NotNull(message = "Атрибут shapeTypeId " + SHAPE_TYPE_ID + " обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_TYPE_ID, required = true)
    private Long shapeTypeId;

}
