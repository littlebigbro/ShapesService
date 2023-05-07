package shapes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shapes.models.Point;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;

@Mapper(componentModel = "spring")
public interface PointMapper {
    PointMapper MAPPER = Mappers.getMapper(PointMapper.class);

    // Out
    PointDTO mapToPointDTO(Point point);
    //Update
    Point mapToPoint(UpdatePointDTO pointDTO);
    //Create
    Point mapToPoint(CreatePointDTO pointDTO);
}
