package shapes.models.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.ShapeFieldNames;
import shapes.models.dto.point.UpdatePointDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeDTO implements ShapeFieldNames {

    @NotNull(message = "Атрибут shapeId (" + SHAPE_ID + ") обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_ID, required = true)
    private Long shapeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = SHAPE_TYPE)
    private ShapeTypeForShapeDTO shapeType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = POINTS)
    private List<UpdatePointDTO> points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = RADIUS_INFO)
    private UpdateRadiusInfoDTO radiusInfo;
}