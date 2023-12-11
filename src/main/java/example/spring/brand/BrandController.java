package example.spring.brand;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/v1/brand")
public class BrandController {

    @Autowired
    private BrandService service;

    @PostMapping("")
    public ResponseEntity<BrandModel> create(@RequestBody @Valid BrandRequestDTO dto) {
        var brand = service.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    @GetMapping("")
    public ResponseEntity<List<BrandModel>> readAll() {
        var brands = service.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandModel> read(@PathVariable UUID id) {
        var brand = service.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandModel> update(@RequestBody @Valid BrandRequestDTO dto,
                                             @PathVariable UUID id) {
        var brand = service.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Brand deleted!");
    }
}
