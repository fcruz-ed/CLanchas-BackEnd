package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.repository.jdbc.LanchaJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanchaService implements BaseService<Lancha> {

    private final LanchaJdbcRepository repository;

    @Autowired
    public LanchaService(LanchaJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lancha save(Lancha lancha) {
        return repository.save(lancha).orElse(null);
    }

    @Override
    public List<Lancha> findAll() {
        return repository.findAll();
    }

    @Override
    public Lancha findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Lancha update(Lancha lancha) {
        return repository.update(lancha).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
