package shapes.models.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.ShapeFieldNames;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.radiusinfo.RadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeDTO implements ShapeFieldNames {

    @ApiModelProperty(value = SHAPE_ID, required = true)
    private Long shapeId;

    @ApiModelProperty(value = CREATED, required = true)
    private LocalDateTime created;

    @ApiModelProperty(value = UPDATED, required = true)
    private LocalDateTime updated;

    @ApiModelProperty(value = SHAPE_TYPE, required = true)
    private ShapeTypeForShapeDTO shapeType;

    @ApiModelProperty(value = POINTS, required = true)
    private List<PointDTO> points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = RADIUS_INFO)
    private RadiusInfoDTO radiusInfo;
}
