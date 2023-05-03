package shapes.models.dto.shape;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

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

    @Nullable
    @ApiModelProperty(value = "Информация о радиусе фигуры")
    private CreateRadiusInfoDTO radiusInfo;
}