package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShapeTypeDTO implements ShapeTypeFieldNames {

    @NotBlank(message = "Атрибут systemName " + SYSTEM_NAME + " обязателен для заполнения")
    @ApiModelProperty(value = SYSTEM_NAME, required = true)
    private String systemName;

    @NotBlank(message = "Атрибут name " + NAME + " обязателен для заполнения")
    @ApiModelProperty(value = NAME, required = true)
    private String name;

    @NotNull(message = "Атрибут pointsCount " + POINTS_COUNT + " обязателен для заполнения")
    @Min(value = 1, message = POINTS_COUNT + " должно быть больше 0")
    @ApiModelProperty(value = POINTS_COUNT, required = true)
    private Integer pointsCount;
}
