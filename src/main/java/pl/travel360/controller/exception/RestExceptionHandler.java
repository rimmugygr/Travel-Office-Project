package pl.travel360.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.travel360.exceptions.ResourceNotFound;
import pl.travel360.exceptions.ResourcesNotProvide;
import pl.travel360.exceptions.UsernameAlreadyExistException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFound.class})
    public ResponseEntity<Object> handleResourceNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourcesNotProvide.class})
    public ResponseEntity<Object> handleResourceNotProvide(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UsernameAlreadyExistException.class})
    public ResponseEntity<Object> handleUsernameAlreadyExistException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
