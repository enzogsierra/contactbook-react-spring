package ar.com.compustack.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.compustack.backend.model.Contact;
import ar.com.compustack.backend.repository.ContactRepository;


@RestController
@RequestMapping("/api/v1")
public class ContactController 
{
    @Autowired
    private ContactRepository contactRepository;


    @GetMapping("/")
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) 
    {
        Optional<Contact> contact = contactRepository.findById(id);

        return (contact.isPresent()) ? (ResponseEntity.ok(contact.get())) : ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Contact contact) 
    {
        contactRepository.save(contact);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Given contact ID doesn't exists."));
        contactRepository.delete(contact);
        return ResponseEntity.ok().build();
    }
}
