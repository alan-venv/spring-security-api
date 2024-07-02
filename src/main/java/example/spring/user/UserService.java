package example.spring.user;

import example.spring.validation.DuplicatedEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public UserModel create(String login, String password, UserRole role) {
    var optUser = repository.findByLogin(login);

    if (optUser == null) {
      var newUser = new UserModel(login, password, role);
      return repository.save(newUser);
    } else {
      throw new DuplicatedEntityException("login already registered");
    }
  }

}
