package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.repository.LanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanchaService implements BaseService<Lancha> {

    @Autowired
    private LanchaRepository lanchaRepository;

    public Lancha save(Lancha lancha) {
        Optional<Lancha> lanchaOptional = lanchaRepository.save(lancha);
        return lanchaOptional.orElse(null);
    }

    public List<Lancha> findAll() {
        return lanchaRepository.findAll();
    }

    public Lancha findById(Long id) {
        Optional<Lancha> lanchaOptional = lanchaRepository.findById(id);
        return lanchaOptional.orElse(null);
    }

    public Lancha update(Lancha lancha) {
        return lanchaRepository.update(lancha).orElse(null);
    }

    public void delete(Long id) {
        lanchaRepository.deleteById(id);
    }

}
