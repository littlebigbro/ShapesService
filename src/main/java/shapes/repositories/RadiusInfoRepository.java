package shapes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shapes.domain.RadiusInfo;

public interface RadiusInfoRepository extends JpaRepository<RadiusInfo, Integer> {
}
