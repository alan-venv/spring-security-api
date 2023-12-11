package example.spring.validation;

public class DuplicatedEntityException extends RuntimeException {

    public DuplicatedEntityException(String message) {
        super(message);
    }
}
