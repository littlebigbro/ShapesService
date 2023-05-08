package shapes.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import shapes.models.Shape;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;

@Mapper(componentModel = "spring",
        uses = {PointListMapper.class, ShapeTypeMapper.class, RadiusInfoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ShapeMapper {

    // Out
    @Mapping(target = "shapeType", source = "shapeType")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "radiusInfo", source = "radiusInfo")
    ShapeDTO mapToShapeDTO(Shape shape);

    //Update
    @Mapping(target = "shapeType", source = "shapeType")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "radiusInfo", source = "radiusInfo")
    Shape mapToShape(UpdateShapeDTO shapeDTO);

    //Create
    @Mapping(target = "shapeType", source = "shapeType")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "radiusInfo", source = "radiusInfo")
    Shape mapToShape(CreateShapeDTO shapeDTO);
}