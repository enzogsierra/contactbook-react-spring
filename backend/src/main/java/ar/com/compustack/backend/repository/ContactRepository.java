package ar.com.compustack.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.compustack.backend.model.Contact;


public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
}
