package shapes.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.action.CalculatedAreaDTO;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;

import java.util.List;

public interface ShapesService {

    ResponseEntity<List<ShapeDTO>> getAll();

    ResponseEntity<ShapeDTO> getById(long id) throws NotFoundException;

    ResponseEntity<HttpStatus> createShape(CreateShapeDTO shapeDTO);

    ResponseEntity<HttpStatus> updateShape(UpdateShapeDTO shapeDTO);

    ResponseEntity<HttpStatus> deleteById(long id) throws NotFoundException;

    ResponseEntity<CalculatedAreaDTO> calculateArea(long id) throws NotFoundException;

    ResponseEntity<ShapeDTO> roll(RollDTO rollDTO) throws NotFoundException;

    ResponseEntity<ShapeDTO> move(MoveDTO moveDTO) throws NotFoundException;

    ResponseEntity<ShapeDTO> scale(ScaleDTO scaleDTO) throws NotFoundException;
}