package shapes.models.dto.point;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.fieldNames.PointFieldNames;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePointDTO implements PointFieldNames {

    @NotNull(message = "Атрибут x (" + X_NAME + ") обязателен для заполнения")
    @ApiModelProperty(value = X_NAME, required = true)
    private Double x;

    @NotNull(message = "Атрибут y (" + Y_NAME + ") обязателен для заполнения")
    @ApiModelProperty(value = Y_NAME, required = true)
    private Double y;
}
