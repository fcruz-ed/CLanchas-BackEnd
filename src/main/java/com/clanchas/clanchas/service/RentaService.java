package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Renta;
import com.clanchas.clanchas.repository.RentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentaService implements BaseService<Renta> {

    @Autowired
    private RentaRepository rentaRepository;

    @Override
    public Renta save(Renta renta) {
        return rentaRepository.save(renta).orElse(null);
    }

    @Override
    public List<Renta> findAll() {
        return rentaRepository.findAll();
    }

    @Override
    public Renta findById(Long id) {
        return rentaRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Renta renta) {
        rentaRepository.update(renta);
    }

    @Override
    public void delete(Long id) {
        rentaRepository.deleteById(id);
    }

    public void updateUso(Long id, boolean uso) {
        rentaRepository.updateUso(id, uso);
    }
}
