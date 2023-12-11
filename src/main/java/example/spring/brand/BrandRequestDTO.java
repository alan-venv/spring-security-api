package example.spring.brand;

import jakarta.validation.constraints.NotBlank;

public record BrandRequestDTO(@NotBlank String name) {

}
