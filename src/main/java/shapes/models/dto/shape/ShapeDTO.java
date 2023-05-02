package shapes.models.dto.shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.radiusinfo.RadiusInfoDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeDTO {
    private int shapeId;
    private Date created;
    private Date updated;
    private ShapeTypeDTO shapeType;
    private List<PointDTO> points;
    private RadiusInfoDTO radiusInfo;
}
