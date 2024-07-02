package example.spring.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<CustomError>> invalidDTO(MethodArgumentNotValidException exception) {
    var errors = exception.getBindingResult().getFieldErrors();
    var message = errors.stream().map(
        error -> new CustomError("controller", error.getField() + " " + error.getDefaultMessage())
    ).collect(Collectors.toCollection(ArrayList::new));
    return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
  }

  @ExceptionHandler(DuplicatedEntityException.class)
  public ResponseEntity<CustomError> duplicatedEntity(DuplicatedEntityException exception) {
    var message = new CustomError("service", exception.getMessage());
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<CustomError> entityNotFound(EntityNotFoundException exception) {
    var message = new CustomError("service", exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<CustomError> messageNotReadable(HttpMessageNotReadableException exception) {
    var message = new CustomError("controller", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<CustomError> badPathVariable(
      MethodArgumentTypeMismatchException exception) {
    var message = new CustomError("controller", exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }
}
