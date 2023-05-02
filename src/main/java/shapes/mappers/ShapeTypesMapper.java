package shapes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shapes.models.ShapeType;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

@Mapper(componentModel = "spring")
public interface ShapeTypesMapper {
    ShapeTypesMapper MAPPER = Mappers.getMapper(ShapeTypesMapper.class);

    // Out
    ShapeTypeDTO mapToShapeTypeDTO(ShapeType shape);
    //Update
    ShapeType mapToShapeType(UpdateShapeTypeDTO shapeDTO);
    //Create
    ShapeType mapToShapeType(CreateShapeTypeDTO shapeDTO);
}
