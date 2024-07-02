package example.spring.brand;

import example.spring.validation.DuplicatedEntityException;
import example.spring.validation.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

  @Autowired
  private BrandRepository repository;

  public BrandModel create(BrandRequestDTO dto) {
    var optBrand = repository.findByName(dto.name());

    if (optBrand.isEmpty()) {
      var newBrand = new BrandModel(dto);
      return repository.save(newBrand);
    } else {
      throw new DuplicatedEntityException("name already registered");
    }
  }

  public BrandModel read(UUID id) {
    var optBrand = repository.findById(id);

    if (optBrand.isPresent()) {
      return optBrand.get();
    } else {
      throw new EntityNotFoundException("no brands with this id could be found");
    }
  }

  public List<BrandModel> readAll() {
    return repository.findAll();
  }

  public BrandModel update(BrandRequestDTO dto, UUID id) {
    var optBrand = repository.findById(id);

    if (optBrand.isPresent()) {
      var brand = optBrand.get();

      var optBrandByName = repository.findByName(dto.name());
      if (optBrandByName.isPresent()) {
        var brandByName = optBrandByName.get();
        if (!Objects.equals(brand.getId(), brandByName.getId())) {
          throw new DuplicatedEntityException("name already registered");
        }
      }
      var newBrand = new BrandModel(dto);
      newBrand.setId(optBrand.get().getId());
      return repository.save(newBrand);
    } else {
      throw new EntityNotFoundException("no brands with this id could be found");
    }
  }

  public void delete(UUID id) {
    var optBrand = repository.findById(id);
    if (optBrand.isPresent()) {
      repository.delete(optBrand.get());
    } else {
      throw new EntityNotFoundException("no brands with this id could be found");
    }
  }
}
