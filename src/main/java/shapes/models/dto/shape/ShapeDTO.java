package shapes.models.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.radiusinfo.RadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeDTO {

    @ApiModelProperty(value = "id фигуры в базе данных", required = true)
    private Long shapeId;

    @ApiModelProperty(value = "Дата создания фигуры", required = true)
    private LocalDateTime created;

    @ApiModelProperty(value = "Дата обновления фигуры", required = true)
    private LocalDateTime updated;

    @ApiModelProperty(value = "Тип фигуры", required = true)
    private ShapeTypeForShapeDTO shapeType;

    @ApiModelProperty(value = "Точки фигуры", required = true)
    private List<PointDTO> points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Информация о радиусе фигуры")
    private RadiusInfoDTO radiusInfo;
}
