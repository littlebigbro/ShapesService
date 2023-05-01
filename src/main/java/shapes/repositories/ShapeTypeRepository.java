package shapes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shapes.domain.ShapeType;

@Repository
public interface ShapeTypeRepository extends JpaRepository<ShapeType, Integer> {
}
