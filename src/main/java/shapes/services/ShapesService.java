package shapes.services;

import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;

import java.util.List;

public interface ShapesService {

    List<ShapeDTO> getAll();

    ShapeDTO getById(int id);

    void createShape(CreateShapeDTO shapeDTO);

    void updateShape(UpdateShapeDTO shapeDTO);

    void deleteById(int id);
}