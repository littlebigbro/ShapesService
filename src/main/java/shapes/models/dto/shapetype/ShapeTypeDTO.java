package shapes.models.dto.shapetype;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeTypeDTO implements ShapeTypeFieldNames {

    @ApiModelProperty(value = SHAPE_TYPE_ID, required = true)
    private Long shapeTypeId;

    @ApiModelProperty(value = SYSTEM_NAME, required = true)
    private String systemName;

    @ApiModelProperty(value = NAME, required = true)
    private String name;

    @ApiModelProperty(value = CREATED, required = true)
    private LocalDateTime created;

    @ApiModelProperty(value = UPDATED, required = true)
    private LocalDateTime updated;

    @ApiModelProperty(value = POINTS_COUNT, required = true)
    private Integer pointsCount;
}
