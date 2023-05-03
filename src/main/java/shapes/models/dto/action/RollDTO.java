package shapes.models.dto.action;

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
public class RollDTO {

    @NotNull
    @ApiModelProperty(value = "id фигуры в базе данных", required = true)
    private Long shapeId;

    @NotNull
    @ApiModelProperty(value = "Угол поворота", required = true)
    private Double angle;
}
