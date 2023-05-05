package shapes.models.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShapeDTO {

    @NotNull
    @ApiModelProperty(value = "Тип фигуры", required = true)
    private ShapeTypeForShapeDTO shapeType;

    @NotNull
    @ApiModelProperty(value = "Точки фигуры", required = true)
    private List<CreatePointDTO> points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Информация о радиусе фигуры")
    private CreateRadiusInfoDTO radiusInfo;
}