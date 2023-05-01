package shapes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shapes.domain.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
}
