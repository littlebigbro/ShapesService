package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.exceptions.NotFoundException;
import shapes.mappers.ShapeTypeMapper;
import shapes.models.ShapeType;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.repositories.ShapeTypeRepository;
import shapes.responses.ValidationErrorResponse;
import shapes.services.ShapeTypeService;

import javax.validation.Valid;
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
                        .map(ShapeTypeMapper.MAPPER::mapToShapeTypeDTO)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeTypeDTO> getById(long id) {
        ShapeType shapeType = shapeTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_TYPE_NOT_FOUND_MSG, id));
        ShapeTypeDTO shapeTypeDTO = ShapeTypeMapper.MAPPER.mapToShapeTypeDTO(shapeType);
        return new ResponseEntity<>(shapeTypeDTO, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<ValidationErrorResponse> createShapeType(@Valid CreateShapeTypeDTO shapeTypeDTO) {
        ShapeType shapeType = ShapeTypeMapper.MAPPER.mapToShapeType(shapeTypeDTO);
        shapeTypeRepository.save(shapeType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<ValidationErrorResponse> updateShapeType(@Valid UpdateShapeTypeDTO shapeTypeDTO) {
        Long shapeTypeId = shapeTypeDTO.getShapeTypeId();
        ShapeType updatedShapeType = shapeTypeRepository.findById(shapeTypeId).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_TYPE_NOT_FOUND_MSG, shapeTypeId));
        updateShapeTypeByDTO(updatedShapeType, shapeTypeDTO);
        shapeTypeRepository.save(updatedShapeType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateShapeTypeByDTO(ShapeType shapeType, UpdateShapeTypeDTO dto) {
        if (dto.getSystemName() != null) {
            shapeType.setSystemName(dto.getSystemName());
        }
        if (dto.getName() != null) {
            shapeType.setName(dto.getName());
        }
        if (dto.getPointsCount() != null) {
            shapeType.setPointsCount(dto.getPointsCount());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> deleteById(long id) {
        if (!shapeTypeRepository.existsById(id)) {
            throw new NotFoundException(NotFoundException.SHAPE_TYPE_NOT_FOUND_MSG, id);
        }
        shapeTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
