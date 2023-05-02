package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.mappers.ShapeTypesMapper;
import shapes.models.ShapeType;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.repositories.ShapeTypeRepository;
import shapes.services.ShapeTypeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShapeTypeServiceImpl implements ShapeTypeService {
    private final ShapeTypeRepository shapeTypeRepository;

    @Override
    public List<ShapeTypeDTO> getAll() {
        List<ShapeType> shapes = shapeTypeRepository.findAll();
        return shapes.stream()
                .map(ShapeTypesMapper.MAPPER::mapToShapeTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShapeTypeDTO getById(int id) {
        Optional<ShapeType> shapeType = shapeTypeRepository.findById(id);
        if (shapeType.isPresent()) {
            return ShapeTypesMapper.MAPPER.mapToShapeTypeDTO(shapeType.get());
        }
        //todo: throw exception;
        return null;
    }

    @Transactional
    @Override
    public void createShapeType(CreateShapeTypeDTO shapeTypeDTO) {
        ShapeType shapeType = ShapeTypesMapper.MAPPER.mapToShapeType(shapeTypeDTO);
        shapeTypeRepository.save(shapeType);
    }

    @Transactional
    @Override
    public void updateShapeType(UpdateShapeTypeDTO shapeTypeDTO) {
        ShapeType shapeType = ShapeTypesMapper.MAPPER.mapToShapeType(shapeTypeDTO);
        shapeTypeRepository.save(shapeType);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        if (shapeTypeRepository.existsById(id)) {
            shapeTypeRepository.deleteById(id);
        }
    }
}
