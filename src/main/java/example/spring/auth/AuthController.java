package example.spring.auth;

import example.spring.user.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO dto) {
    var credentials = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
    var auth = authenticationManager.authenticate(credentials);
    var user = (UserModel) auth.getPrincipal();
    var token = tokenService.generateToken(user);

    return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(token));
  }
}
