package shapes.repositories;

import shapes.domain.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShapesRepository extends JpaRepository<Shape, Integer> {
}
