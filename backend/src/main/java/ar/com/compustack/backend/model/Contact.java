package ar.com.compustack.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "contacts")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Contact 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, message = "El nombre debe tener al menos {min} caracteres")
    private String name;

    @Size(min = 3, message = "El apellido debe tener al menos {min} caracteres")
    private String lastName;

    @NotBlank(message = "Indica un número de teléfono para este contacto")
    private String phoneNumber;

    @Email(message = "El email '${validatedValue}' no es un email válido. Escribe una dirección de email válida")
    private String email;
}
