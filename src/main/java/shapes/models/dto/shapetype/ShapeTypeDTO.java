package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeTypeDTO implements ShapeTypeFieldNames {

    @NotNull
    @ApiModelProperty(value = SHAPE_TYPE_ID, required = true)
    private Long shapeTypeId;

    @NotNull
    @ApiModelProperty(value = SYSTEM_NAME, required = true)
    private String systemName;

    @NotNull
    @ApiModelProperty(value = NAME, required = true)
    private String name;

    @ApiModelProperty(value = CREATED, required = true)
    private LocalDateTime created;

    @ApiModelProperty(value = UPDATED, required = true)
    private LocalDateTime updated;

    @ApiModelProperty(value = POINTS_COUNT, required = true)
    private Integer pointsCount;
}
