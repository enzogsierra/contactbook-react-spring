package ar.com.compustack.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Contact name can't be null")
    @Min(value = 3, message = "Name should be at least 3 chars long")
    private String name;

    @NotNull(message = "Contact last name can't be null")
    @Min(value = 3, message = "Last name should be at least 3 chars long")
    private String lastName;

    @NotNull(message = "Contact phone number can't be null")
    @NotBlank(message = "Provide a phone number for this contact")
    private String phoneNumber;

    @Email(message = "Please provide a valid email")
    private String email;
}
