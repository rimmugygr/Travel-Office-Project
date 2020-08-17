package pl.travel360.exceptions;

import java.util.function.Supplier;

public class AuthoritiesNotFoundException extends RuntimeException{

    public AuthoritiesNotFoundException() {
    }

    public AuthoritiesNotFoundException(String message) {
        super(message);
    }
}
