package com.example.logging.config;

import com.example.logging.utils.GenericResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationErrorHandlers {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public GenericResponse<Map<String, String>> handleValidationExceptions(
      final MethodArgumentNotValidException ex) {
    final Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      final String field = ((FieldError) error).getField();
      final String message = error.getDefaultMessage();
      errors.put(field, message);
    });

    return GenericResponse.failure("Bad request", errors);
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(Exception.class)
  public GenericResponse<Void> handleOtherExceptions(final Exception ex) {
    log.error("Global exception caught", ex);
    return GenericResponse.failure("There was a problem with your request");
  }
}
