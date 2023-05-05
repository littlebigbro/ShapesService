package shapes.models.dto.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.point.UpdatePointDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeDTO {

    @NotNull
    @ApiModelProperty(value = "id фигуры в базе данных", required = true)
    private Long shapeId;

    @NotNull
    @ApiModelProperty(value = "Дата создания фигуры", required = true)
    private LocalDateTime created;

    @NotNull
    @ApiModelProperty(value = "Тип фигуры", required = true)
    private ShapeTypeForShapeDTO shapeType;

    @NotNull
    @ApiModelProperty(value = "Точки фигуры", required = true)
    private List<UpdatePointDTO> points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "Информация о радиусе фигуры")
    private UpdatePointDTO radiusInfo;
}