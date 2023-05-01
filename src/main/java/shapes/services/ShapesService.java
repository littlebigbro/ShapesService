package shapes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.domain.Shape;
import shapes.repositories.ShapesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShapesService {
    private final ShapesRepository shapesRepository;

    @Autowired
    public ShapesService(ShapesRepository shapesRepository) {
        this.shapesRepository = shapesRepository;
    }

    @Transactional
    public List<Shape> getAll() {
        return shapesRepository.findAll();
    }

    @Transactional
    public Shape getById(int id) {
        Optional<Shape> optional = shapesRepository.findById(id);
        return optional.orElse(null);
    }

    @Transactional
    public void save(Shape shape) {
        shapesRepository.save(shape);
    }

    @Transactional
    public void deleteById(int id) {
        if (shapesRepository.existsById(id)) {
            shapesRepository.deleteById(id);
        }
    }
}