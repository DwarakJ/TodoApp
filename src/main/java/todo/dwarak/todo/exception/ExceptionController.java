package todo.dwarak.todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import todo.dwarak.todo.model.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<?> playlistNotFound(PersonNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResponseStatusError(ErrorCodes.PERSON_NOT_FOUND.code(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> songNotFound(TodoNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResponseStatusError(ErrorCodes.TODO_NOT_FOUND.code(), ex.getMessage()));
    }

}

@Data
@AllArgsConstructor
class ResponseStatusError {

    private int status;
    private String message;
}
