package shapes.models.dto.shapetype;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.ShapeTypeFieldNames;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeTypeDTO implements ShapeTypeFieldNames {

    @NotNull(message = "Атрибут shapeTypeId (" + SHAPE_TYPE_ID + ") обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_TYPE_ID, required = true)
    private Long shapeTypeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = SYSTEM_NAME)
    private String systemName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = NAME)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 1, message = POINTS_COUNT + " должно быть больше 0")
    @ApiModelProperty(value = POINTS_COUNT)
    private Integer pointsCount;
}