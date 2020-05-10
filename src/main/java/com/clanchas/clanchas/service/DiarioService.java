package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Diario;
import com.clanchas.clanchas.repository.DiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiarioService implements BaseService<Diario> {

    private final DiarioRepository repository;

    @Autowired
    public DiarioService(DiarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Diario save(Diario diario) {
        return repository.save(diario).orElse(null);
    }

    @Override
    public List<Diario> findAll() {
        return repository.findAll();
    }

    @Override
    public Diario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Diario update(Diario diario) {
        return repository.update(diario).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
