package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeTypeDTO implements ShapeTypeFieldNames {

    @NotNull(message = "Атрибут shapeTypeId " + SHAPE_TYPE_ID + " обязателен для заполнения")
    @ApiModelProperty(value = SHAPE_TYPE_ID, required = true)
    private Long shapeTypeId;

    @NotBlank(message = "Атрибут systemName " + SYSTEM_NAME + " обязателен для заполнения")
    @ApiModelProperty(value = SYSTEM_NAME, required = true)
    private String systemName;

    @NotBlank(message = "Атрибут name " + NAME + " обязателен для заполнения")
    @ApiModelProperty(value = NAME, required = true)
    private String name;

    @NotNull(message = "Атрибут created " + CREATED + " обязателен для заполнения")
    @ApiModelProperty(value = CREATED, required = true)
    private LocalDateTime created;

    @NotNull(message = "Атрибут pointsCount " + POINTS_COUNT + " обязателен для заполнения")
    @Min(value = 1, message = POINTS_COUNT + " должно быть больше 0")
    @ApiModelProperty(value = POINTS_COUNT, required = true)
    private Integer pointsCount;
}
