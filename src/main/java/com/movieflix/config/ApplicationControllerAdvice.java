package com.movieflix.config;

import com.movieflix.controller.response.ErrorResponse;
import com.movieflix.exception.UsernameOrPasswordInvalid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UsernameOrPasswordInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUsernameOrPasswordInvalid(
            UsernameOrPasswordInvalid exception,
            HttpServletRequest request
    ) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, String> fieldErrors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
                fieldErrors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });

        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .fieldErrors(fieldErrors)
                .message("Validation error")
                .path(request.getRequestURI())
                .build();


    }
}
