package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeTypeDTO {

    @NotNull
    @ApiModelProperty(value = "id типа фигуры в базе данных", required = true)
    private Long shapeTypeId;

    @NotNull
    @ApiModelProperty(value = "Системное наименование типа фигуры", required = true)
    private String systemName;

    @NotNull
    @ApiModelProperty(value = "Наименование типа фигуры", required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "Дата создания типа фигуры", required = true)
    private LocalDateTime created;

    @NotNull
    @ApiModelProperty(value = "Дата обновления типа фигуры", required = true)
    private LocalDateTime updated;
}
