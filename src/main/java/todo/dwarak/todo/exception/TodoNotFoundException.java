package todo.dwarak.todo.exception;

import java.math.BigInteger;

@SuppressWarnings("serial")
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(final BigInteger id) {
        super(String.format("song with id '%s' not found", id));
    }
}
