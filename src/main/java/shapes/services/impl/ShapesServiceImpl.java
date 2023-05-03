package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.exceptions.NotFoundException;
import shapes.mappers.ShapesMapper;
import shapes.models.Shape;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.repositories.ShapesRepository;
import shapes.services.ShapesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShapesServiceImpl implements ShapesService {
    private final ShapesRepository shapesRepository;

    @Override
    public ResponseEntity<List<ShapeDTO>> getAll() {
        List<Shape> shapes = shapesRepository.findAll();
        return new ResponseEntity<>(
                shapes.stream()
                        .map(ShapesMapper.MAPPER::mapToShapeDTO)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> getById(int id) throws NotFoundException {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(id, Shape.class));
        ShapeDTO shapeDTO = ShapesMapper.MAPPER.mapToShapeDTO(shape);
        return new ResponseEntity<>(shapeDTO, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> createShape(CreateShapeDTO shapeDTO) {
        Shape shape = ShapesMapper.MAPPER.mapToShape(shapeDTO);
        shapesRepository.save(shape);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> updateShape(UpdateShapeDTO shapeDTO) {
        Shape shape = ShapesMapper.MAPPER.mapToShape(shapeDTO);
        shapesRepository.save(shape);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> deleteById(int id) throws NotFoundException {
        if (!shapesRepository.existsById(id)) {
            throw new NotFoundException(id, Shape.class);
        }
        shapesRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}