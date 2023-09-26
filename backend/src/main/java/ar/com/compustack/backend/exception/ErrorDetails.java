package ar.com.compustack.backend.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class ErrorDetails 
{
    private Date timestamp = new Date();
    private String message;
    private Map<String, String> errors = new HashMap<>();

    
    // Mapear un BindingResult fieldErrors dentro de la propiedad "errors"
    public void mapErrors(List<FieldError> errors)
    {
        for(FieldError field: errors) { // Iterar sobre todos los fields de error
            this.errors.put(field.getField(), field.getDefaultMessage()); // Añadir el field junto con el mensaje al mapeo de errors
        }
    }
}
