package it.sara.demo.exception;

import it.sara.demo.dto.StatusDTO;
import it.sara.demo.web.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<GenericResponse<?>> handleGenericException(GenericException ex) {
        StatusDTO status = ex.getStatus();
        return ResponseEntity.ok(
                GenericResponse.error(status.getCode(), status.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                           .stream()
                           .map(err -> err.getDefaultMessage())
                           .collect(Collectors.joining("; "));
        return ResponseEntity.ok(GenericResponse.error(400, message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<?>> handleOtherExceptions(Exception ex) {
        return ResponseEntity.ok(GenericResponse.error(500, ex.getMessage()));
    }
}
