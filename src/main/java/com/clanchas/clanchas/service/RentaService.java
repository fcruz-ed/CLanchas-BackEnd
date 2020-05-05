package com.clanchas.clanchas.service;

import com.clanchas.clanchas.model.Renta;
import com.clanchas.clanchas.repository.RentaRepository;
import com.clanchas.clanchas.repository.jdbc.RentaJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentaService implements BaseService<Renta> {

    //@Autowired
//    private final RentaRepository rentaRepository;
//
//    @Autowired
//    public RentaService(RentaRepository rentaRepository) {
//        this.rentaRepository = rentaRepository;
//    }

    private final RentaJdbcRepository repository;

    @Autowired
    public RentaService(RentaJdbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Renta save(Renta renta) {
        return repository.save(renta).orElse(null);
    }

    @Override
    public List<Renta> findAll() {
        return repository.findAll();
    }

    @Override
    public Renta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Renta update(Renta renta) {
        return repository.update(renta).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Renta updateUso(Long id, boolean uso) {
        return repository.updateUso(id, uso).orElse(null);
    }
/*
    @Override
    public Renta save(Renta renta) {
        return (Renta) rentaRepository.save(renta).orElse(null);
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
    public Renta update(Renta renta) {
        return rentaRepository.update(renta).orElse(null);
    }

    @Override
    public void delete(Long id) {
        rentaRepository.deleteById(id);
    }

    public void updateUso(Long id, boolean uso) {
        rentaRepository.updateUso(id, uso);
    }

 */
}
