package shapes.dto.shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.domain.Point;
import shapes.domain.RadiusInfo;
import shapes.domain.ShapeType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeDTO {
    private ShapeType shapeType;
    private RadiusInfo radiusInfo;
    private boolean deleted;
    private List<Point> points;
}
