package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Precio;
import com.clanchas.clanchas.model.Renta;
import com.clanchas.clanchas.repository.jdbc.PrecioJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecioService implements BaseService<Precio> {

    private final PrecioJdbcRepository repository;

    @Autowired
    public PrecioService(PrecioJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Precio save(Precio precio) {
        return repository.save(precio).orElse(null);
    }

    @Override
    public List<Precio> findAll() {
        return repository.findAll();
    }

    @Override
    public Precio findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Precio update(Precio precio) {
        return repository.update(precio).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
