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
public class CalculatedAreaDTO {

    @NotNull
    @ApiModelProperty(value = "Площадь фигуры", required = true)
    private Double shapeArea;
}