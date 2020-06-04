package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Persona;
import com.clanchas.clanchas.repository.jdbc.PersonaJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements BaseService<Persona> {

    private final PersonaJdbcRepository repository;

    @Autowired
    public PersonaService(PersonaJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Persona save(Persona persona) {
        return repository.save(persona).orElse(null);
    }

    @Override
    public List<Persona> findAll() {
        return repository.findAll();
    }

    @Override
    public Persona findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Persona update(Persona persona) {
        return repository.update(persona).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
