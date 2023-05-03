package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.exceptions.NotFoundException;
import shapes.mappers.ShapeTypesMapper;
import shapes.models.ShapeType;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.repositories.ShapeTypeRepository;
import shapes.services.ShapeTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShapeTypeServiceImpl implements ShapeTypeService {
    private final ShapeTypeRepository shapeTypeRepository;

    @Override
    public ResponseEntity<List<ShapeTypeDTO>> getAll() {
        List<ShapeType> shapes = shapeTypeRepository.findAll();
        return new ResponseEntity<>(
                shapes.stream()
                        .map(ShapeTypesMapper.MAPPER::mapToShapeTypeDTO)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeTypeDTO> getById(long id) throws NotFoundException {
        ShapeType shapeType = shapeTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(id, ShapeType.class));
        ShapeTypeDTO shapeTypeDTO = ShapeTypesMapper.MAPPER.mapToShapeTypeDTO(shapeType);
        return new ResponseEntity<>(shapeTypeDTO, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> createShapeType(CreateShapeTypeDTO shapeTypeDTO) {
        ShapeType shapeType = ShapeTypesMapper.MAPPER.mapToShapeType(shapeTypeDTO);
        shapeTypeRepository.save(shapeType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> updateShapeType(UpdateShapeTypeDTO shapeTypeDTO) {
        ShapeType shapeType = ShapeTypesMapper.MAPPER.mapToShapeType(shapeTypeDTO);
        shapeTypeRepository.save(shapeType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> deleteById(long id) throws NotFoundException {
        if (!shapeTypeRepository.existsById(id)) {
            throw new NotFoundException(id, ShapeType.class);
        }
        shapeTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
