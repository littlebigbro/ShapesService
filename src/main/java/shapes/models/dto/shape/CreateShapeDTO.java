package shapes.models.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.ShapeFieldNames;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShapeDTO implements ShapeFieldNames {

    @NotNull(message = "Атрибут shapeType (" + SHAPE_TYPE + ") обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_TYPE, required = true)
    private ShapeTypeForShapeDTO shapeType;

    @NotNull(message = "Атрибут points (" + POINTS + ") обязателен для заполнения")
    @ApiModelProperty(value = POINTS, required = true)
    private List<CreatePointDTO> points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = RADIUS_INFO)
    private CreateRadiusInfoDTO radiusInfo;
}