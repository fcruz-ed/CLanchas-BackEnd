package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Uso;
import com.clanchas.clanchas.repository.jdbc.UsoJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsoService implements BaseService<Uso> {

    private final UsoJdbcRepository repository;

    @Autowired
    public UsoService(UsoJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Uso save(Uso uso) {
        return repository.save(uso).orElse(null);
    }

    @Override
    public List<Uso> findAll() {
        return repository.findAll();
    }

    @Override
    public Uso findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Uso update(Uso uso) {
        return repository.update(uso).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
