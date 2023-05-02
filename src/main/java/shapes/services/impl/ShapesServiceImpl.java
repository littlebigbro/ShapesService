package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.mappers.ShapesMapper;
import shapes.models.Shape;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.repositories.ShapesRepository;
import shapes.services.ShapesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShapesServiceImpl implements ShapesService {
    private final ShapesRepository shapesRepository;

    @Override
    public List<ShapeDTO> getAll() {
        List<Shape> shapes = shapesRepository.findAll();
        return shapes.stream()
                .map(ShapesMapper.MAPPER::mapToShapeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShapeDTO getById(int id) {
        Optional<Shape> shape = shapesRepository.findById(id);
        if (shape.isPresent()) {
            return ShapesMapper.MAPPER.mapToShapeDTO(shape.get());
        }
        //todo: throw exception
        return null;
    }

    @Transactional
    @Override
    public void createShape(CreateShapeDTO shapeDTO) {
        Shape shape = ShapesMapper.MAPPER.mapToShape(shapeDTO);
        shapesRepository.save(shape);
    }

    @Transactional
    @Override
    public void updateShape(UpdateShapeDTO shapeDTO) {
        Shape shape = ShapesMapper.MAPPER.mapToShape(shapeDTO);
        shapesRepository.save(shape);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if (shapesRepository.existsById(id)) {
            shapesRepository.deleteById(id);
        }
    }
}