package shapes.models.dto.radiusinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRadiusInfoDTO {

    @ApiModelProperty(value = "id радиуса в базе данных", required = true)
    private int radiusInfoId;

    @ApiModelProperty(value = "Радиус", required = true)
    private double radius;
}
