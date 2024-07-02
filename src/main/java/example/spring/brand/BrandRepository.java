package example.spring.brand;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandModel, UUID> {

  Optional<BrandModel> findByName(String name);
}
