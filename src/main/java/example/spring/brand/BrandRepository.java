package example.spring.brand;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<BrandModel, UUID> {

    Optional<BrandModel> findByName(String name);
}
