package ar.com.compustack.backend.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler 
{
    // Entidad no encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) 
    {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    // El request body no está presente
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage());
        return ResponseEntity.status(status).body(details);
    }

    // Errores de validación al guardar la entidad
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        BindingResult result = ex.getBindingResult(); // Obtener los resultados de la validación

        ErrorDetails details = new ErrorDetails();
        details.setMessage("Por favor, rellena los campos requeridos");
        details.mapErrors(result.getFieldErrors());
        return ResponseEntity.status(status).body(details);
    }

    // Request method no soportado (get, post, etc...)
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) 
    {
        ErrorDetails details = new ErrorDetails();
        details.setMessage(ex.getMessage() + " for " + request.getDescription(false));
        return ResponseEntity.status(status).body(details);
    }
}
