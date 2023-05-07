package shapes.models.dto.point;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.PointFieldNames;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO implements PointFieldNames {

    @ApiModelProperty(value = POINT_ID, required = true)
    private Long pointId;

    @ApiModelProperty(value = X_NAME, required = true)
    private Double x;

    @ApiModelProperty(value = Y_NAME, required = true)
    private Double y;
}
