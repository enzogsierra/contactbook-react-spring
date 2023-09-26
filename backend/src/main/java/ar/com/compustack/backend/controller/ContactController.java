package ar.com.compustack.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.compustack.backend.model.Contact;
import ar.com.compustack.backend.repository.ContactRepository;


@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ContactController 
{
    @Autowired
    private ContactRepository contactRepository;


    @GetMapping("/") // Principal
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}") // Buscar contacto por ID
    public ResponseEntity<?> findById(@PathVariable Integer id)
    {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el ID del contacto"));
        return ResponseEntity.ok(contact);
    }

    @PostMapping("/save") // Crear/editar contact
    public ResponseEntity<?> save(@Valid @RequestBody Contact contact)
    {
        contactRepository.save(contact);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/delete/{id}") // Eliminar contacto
    public ResponseEntity<?> delete(@PathVariable Integer id) 
    {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El ID de contacto dado no existe"));
        contactRepository.delete(contact);
        return ResponseEntity.ok().build();
    }
}
