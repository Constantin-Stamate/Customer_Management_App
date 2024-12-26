package md.Management_Client.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionModel> notFoundEntity(NotFoundException e, HttpServletRequest request) {

        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setTimestamp(Instant.now().toString());
        exceptionModel.setError("Entity not found");
        exceptionModel.setStatus((long) HttpStatus.NOT_FOUND.value());
        exceptionModel.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionModel);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionModel> alreadyExistsEntity(AlreadyExistsException e, HttpServletRequest request) {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setTimestamp(Instant.now().toString());
        exceptionModel.setError("Entity already exists");
        exceptionModel.setStatus((long) HttpStatus.CONFLICT.value());
        exceptionModel.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setTimestamp(Instant.now().toString());
        exceptionModel.setError(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
        exceptionModel.setStatus((long) HttpStatus.BAD_REQUEST.value());
        exceptionModel.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionModel);
    }
}