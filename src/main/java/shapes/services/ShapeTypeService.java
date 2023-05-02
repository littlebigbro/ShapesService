package shapes.services;

import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

import java.util.List;

public interface ShapeTypeService {

    List<ShapeTypeDTO> getAll();

    ShapeTypeDTO getById(int id);

    void createShapeType(CreateShapeTypeDTO shapeTypeDTO);

    void updateShapeType(UpdateShapeTypeDTO shapeTypeDTO);

    void deleteById(int id);
}
