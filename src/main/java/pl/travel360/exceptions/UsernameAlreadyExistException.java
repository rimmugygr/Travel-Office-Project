package pl.travel360.exceptions;

public class UsernameAlreadyExistException extends  RuntimeException {
    public UsernameAlreadyExistException() {
    }

    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
