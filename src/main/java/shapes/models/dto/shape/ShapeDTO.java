package shapes.models.dto.shape;

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
    private int shapeId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private ShapeTypeForShapeDTO shapeType;
    private List<PointDTO> points;
    private RadiusInfoDTO radiusInfo;
}
