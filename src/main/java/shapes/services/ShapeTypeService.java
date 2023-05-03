package shapes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

import java.util.List;

public interface ShapeTypeService {

    ResponseEntity<List<ShapeTypeDTO>> getAll();

    ResponseEntity<ShapeTypeDTO> getById(int id) throws NotFoundException;

    ResponseEntity<HttpStatus> createShapeType(CreateShapeTypeDTO shapeTypeDTO);

    ResponseEntity<HttpStatus> updateShapeType(UpdateShapeTypeDTO shapeTypeDTO);

    ResponseEntity<HttpStatus> deleteById(int id) throws NotFoundException;
}
