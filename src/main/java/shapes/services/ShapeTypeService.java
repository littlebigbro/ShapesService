package shapes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.responses.ValidationErrorResponse;

import java.util.List;

public interface ShapeTypeService {

    ResponseEntity<List<ShapeTypeDTO>> getAll();

    ResponseEntity<ShapeTypeDTO> getById(long id) throws NotFoundException;

    ResponseEntity<ValidationErrorResponse> createShapeType(CreateShapeTypeDTO shapeTypeDTO);

    ResponseEntity<ValidationErrorResponse> updateShapeType(UpdateShapeTypeDTO shapeTypeDTO);

    ResponseEntity<HttpStatus> deleteById(long id) throws NotFoundException;
}
