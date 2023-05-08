package shapes.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import shapes.models.Point;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = PointMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PointListMapper {

    List<PointDTO> mapToPointsDTO(List<Point> pointList);

    //Create
    List<Point> mapToPointList(List<CreatePointDTO> pointDTOList);

    //Update
    List<Point> toPointList(List<UpdatePointDTO> pointDTOList);

}