package shapes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;

import java.util.List;

public interface ShapesService {

    ResponseEntity<List<ShapeDTO>> getAll();

    ResponseEntity<ShapeDTO> getById(int id) throws NotFoundException;

    ResponseEntity<HttpStatus> createShape(CreateShapeDTO shapeDTO);

    ResponseEntity<HttpStatus> updateShape(UpdateShapeDTO shapeDTO);

    ResponseEntity<HttpStatus> deleteById(int id) throws NotFoundException;
}