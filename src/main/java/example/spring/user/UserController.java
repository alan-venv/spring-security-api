package example.spring.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto) {
        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        var user = service.create(dto.login(), encryptedPassword, dto.role());
        var response = new UserResponseDTO(user.getUsername(), user.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
