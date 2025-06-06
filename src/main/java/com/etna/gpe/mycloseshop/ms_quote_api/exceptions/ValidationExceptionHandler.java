package com.etna.gpe.mycloseshop.ms_quote_api.exceptions;

import com.etna.gpe.mycloseshop.common_api.ms_login.dto.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", 400);
        errors.put("errors", new HashMap<>());
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            ((Map<String, Object>) errors.get("errors")).put(fieldName, errorMessage);
        });
        ResponseError responseError = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                errors
        );
        return ResponseEntity.badRequest().body(responseError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        // use ex
        Map<String, Object> errors = new HashMap<>();
        errors.put("details", ex.getMessage());

        ResponseError responseError = new ResponseError(
                HttpStatus.BAD_REQUEST.value(),
                "Malformed JSON request",
                errors
        );
        return ResponseEntity.badRequest().body(responseError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleAllUncaught(
            Exception ex,
            WebRequest request
    ) {
        ResponseError error = new ResponseError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                Map.of(
                        "timestamp", Instant.now(),
                        "message", ex.getMessage(),
                        "path", request.getDescription(false)
                )
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
