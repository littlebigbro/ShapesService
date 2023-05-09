package shapes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shapes.models.Shape;

@Repository
public interface ShapesRepository extends JpaRepository<Shape, Long> {
}
