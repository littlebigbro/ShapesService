package shapes.models.dto.point;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePointDTO {

    @NotNull
    @ApiModelProperty(value = "id точки в базе данных", required = true)
    private Long pointId;

    @NotNull
    @ApiModelProperty(value = "Координата X", required = true)
    private Double x;

    @NotNull
    @ApiModelProperty(value = "Координата Y", required = true)
    private Double y;
}

