package shapes.models.dto.radiusinfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.RadiusInfoFieldNames;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRadiusInfoDTO implements RadiusInfoFieldNames {

    @NotNull(message = "Атрибут radius (" + RADIUS + ") обязателен для заполнения")
    @ApiModelProperty(value = RADIUS, required = true)
    private Double radius;
}
