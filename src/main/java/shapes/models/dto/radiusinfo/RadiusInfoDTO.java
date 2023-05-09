package shapes.models.dto.radiusinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.RadiusInfoFieldNames;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RadiusInfoDTO implements RadiusInfoFieldNames {

    @ApiModelProperty(value = RADIUS_INFO_ID, required = true)
    private Long radiusInfoId;

    @ApiModelProperty(value = RADIUS, required = true)
    private Double radius;
}
