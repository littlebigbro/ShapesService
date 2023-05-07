package shapes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shapes.models.Point;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = PointMapper.class)
public interface PointListMapper {
    PointListMapper MAPPER = Mappers.getMapper(PointListMapper.class);

    List<PointDTO> toPointDTo(List<Point> pointList);
    //Create
    List<Point> mapToPointList(List<CreatePointDTO> pointDTOList);
    //Update
    List<Point> toPointList(List<UpdatePointDTO> pointDTOList);

}