package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Precio;
import com.clanchas.clanchas.repository.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecioService implements BaseService<Precio> {

    @Autowired
    private PrecioRepository precioRepository;

    public Precio save(Precio tp) {
        Optional<Precio> tabuladorPreciosOptional = precioRepository.save(tp);
        return tabuladorPreciosOptional.orElse(null);
    }

    public List<Precio> findAll() {
        return precioRepository.findAll();
    }

    public Precio findById(Long id) {
        Optional<Precio> tabuladorPreciosOptional = precioRepository.findById(id);
        return tabuladorPreciosOptional.orElse(null);
    }

    public void update(Precio precio) {
        precioRepository.update(precio);
    }

    public void delete(Long id) {
        precioRepository.deleteById(id);
    }

}
