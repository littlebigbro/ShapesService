package shapes.models.dto.point;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO {

    @ApiModelProperty(value = "id точки в базе данных", required = true)
    private int pointId;

    @ApiModelProperty(value = "Координата X", required = true)
    private double x;

    @ApiModelProperty(value = "Координата Y", required = true)
    private double y;
}
