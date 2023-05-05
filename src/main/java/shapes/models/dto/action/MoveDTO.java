package shapes.models.dto.action;

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
public class MoveDTO {

    @NotNull
    @ApiModelProperty(value = "id фигуры в базе данных", required = true)
    private Long shapeId;

    @NotNull
    @ApiModelProperty(value = "Координата X", required = true)
    private Double x;

    @NotNull
    @ApiModelProperty(value = "Координата Y", required = true)
    private Double y;
}
