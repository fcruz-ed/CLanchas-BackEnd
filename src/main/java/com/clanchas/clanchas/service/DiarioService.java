package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Diario;
import com.clanchas.clanchas.repository.DiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiarioService implements BaseService<Diario> {

    @Autowired
    private DiarioRepository diarioRepository;

    @Override
    public Diario save(Diario obj) {
        return null;
    }

    @Override
    public List<Diario> findAll() {
        return diarioRepository.findAll();
    }

    @Override
    public Diario findById(Long id) {
        return null;
    }

    @Override
    public void update(Diario obj) {

    }

    @Override
    public void delete(Long id) {

    }
}
