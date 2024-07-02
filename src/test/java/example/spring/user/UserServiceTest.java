package example.spring.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

  @Autowired
  private UserService service;

  @Autowired
  private UserRepository repository;

  @Test
  void create() {
    var user = service.create("teste@teste.com", "123", UserRole.USER);

    assertNotNull(user.getId());
  }
}