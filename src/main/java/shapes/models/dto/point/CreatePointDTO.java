package shapes.models.dto.point;

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
public class CreatePointDTO {

    @NotNull
    @ApiModelProperty(value = "Координата X", required = true)
    private Double x;

    @NotNull
    @ApiModelProperty(value = "Координата Y", required = true)
    private Double y;
}
