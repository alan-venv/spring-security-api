package example.spring.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(@NotBlank String login, @NotBlank String password) {

}
