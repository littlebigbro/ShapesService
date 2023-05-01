package shapes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.domain.ShapeType;
import shapes.repositories.ShapeTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShapeTypeService {
    private final ShapeTypeRepository shapeTypeRepository;

    @Autowired
    public ShapeTypeService(ShapeTypeRepository shapeTypeRepository) {
        this.shapeTypeRepository = shapeTypeRepository;
    }

    @Transactional
    public List<ShapeType> getAll() {
        return shapeTypeRepository.findAll();
    }

    @Transactional
    public ShapeType getById(int id) {
        Optional<ShapeType> optional = shapeTypeRepository.findById(id);
        return optional.orElse(null);
    }

    @Transactional
    public void save(ShapeType shapeType) {
        shapeTypeRepository.save(shapeType);
    }

    @Transactional
    public void deleteById(int id) {
        if (shapeTypeRepository.existsById(id)) {
            shapeTypeRepository.deleteById(id);
        }
    }
}
