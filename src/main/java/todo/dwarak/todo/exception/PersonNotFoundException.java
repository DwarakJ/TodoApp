package todo.dwarak.todo.exception;

import java.math.BigInteger;

@SuppressWarnings("serial")
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(final BigInteger id) {
        super(String.format("person with id '%s' not found", id));
    }
}
