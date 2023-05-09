package shapes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shapes.exceptions.ShapeValidationException;
import shapes.models.dto.action.CalculatedAreaDTO;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.responses.ValidationErrorResponse;

import java.util.List;

public interface ShapesService {

    ResponseEntity<List<ShapeDTO>> getAll();

    ResponseEntity<ShapeDTO> getById(long id);

    ResponseEntity<ValidationErrorResponse> createShape(CreateShapeDTO shapeDTO) throws ShapeValidationException;

    ResponseEntity<ValidationErrorResponse> updateShape(UpdateShapeDTO shapeDTO) throws ShapeValidationException;

    ResponseEntity<HttpStatus> deleteById(long id);

    ResponseEntity<CalculatedAreaDTO> calculateArea(long id);

    ResponseEntity<ShapeDTO> roll(RollDTO rollDTO);

    ResponseEntity<ShapeDTO> move(MoveDTO moveDTO);

    ResponseEntity<ShapeDTO> scale(ScaleDTO scaleDTO);
}