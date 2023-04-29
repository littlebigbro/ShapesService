package shapes.services;

import shapes.domain.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.repositories.ShapesRepository;

@Service
public class ShapesService {
    private final ShapesRepository shapesRepository;

    @Autowired
    public ShapesService(ShapesRepository shapesRepository) {
        this.shapesRepository = shapesRepository;
    }

    @Transactional
    public void add(Shape shape) {
        shapesRepository.save(shape);
    }
}
