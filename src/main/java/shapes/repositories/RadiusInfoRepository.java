package shapes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shapes.models.RadiusInfo;

public interface RadiusInfoRepository extends JpaRepository<RadiusInfo, Long> {
}
