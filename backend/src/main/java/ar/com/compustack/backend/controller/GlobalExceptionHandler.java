package ar.com.compustack.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler 
{
    // Request body is not present
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        return ResponseEntity.status(status).body(ex.getMessage());
    }

    // Entity validation errors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        BindingResult result = ex.getBindingResult(); // Get validation results
        Map<String, String> errors = new HashMap<>(); // Map that will store "{field}": "{message}"

        for(FieldError field: result.getFieldErrors()) { // Iterate over all field errors
            errors.put(field.getField(), field.getDefaultMessage()); // Add field error to errors list
        }

        // Send response info
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date()); 
        response.put("message", "Please fill the required fields"); // Global message
        response.put("errors", errors); // Validation info
        return ResponseEntity.status(status).body(response);
    }
}
