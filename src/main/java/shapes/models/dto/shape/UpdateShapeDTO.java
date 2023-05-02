package shapes.models.dto.shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shapes.models.dto.point.UpdatePointDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShapeDTO {
    private int shapeId;
    private UpdateShapeTypeDTO shapeType;
    private List<UpdatePointDTO> points;
    private UpdateRadiusInfoDTO radiusInfo;
}