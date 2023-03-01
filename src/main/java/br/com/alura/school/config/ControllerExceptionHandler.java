package br.com.alura.school.config;

import br.com.alura.school.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException exception) {
        return buildResponseEntityErro(HttpStatus.BAD_REQUEST, exception);
    }

    private ResponseEntity<Object> buildResponseEntityErro(HttpStatus httpStatus, Exception exception) {
        return new ResponseEntity<>(
                new ResponseErro(httpStatus.value(),httpStatus.getReasonPhrase(),exception.getMessage()),
                httpStatus
        );
    }
}
