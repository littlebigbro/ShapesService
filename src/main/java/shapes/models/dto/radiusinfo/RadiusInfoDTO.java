package shapes.models.dto.radiusinfo;

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
public class RadiusInfoDTO {

    @NotNull
    @ApiModelProperty(value = "id радиуса в базе данных", required = true)
    private Long radiusInfoId;

    @NotNull
    @ApiModelProperty(value = "Радиус", required = true)
    private Double radius;
}
