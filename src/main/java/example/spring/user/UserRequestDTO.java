package example.spring.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
    @NotBlank String login,
    @NotBlank String password,
    @NotNull UserRole role
) {

}
