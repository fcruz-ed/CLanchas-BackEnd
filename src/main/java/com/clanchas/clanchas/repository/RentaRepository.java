package com.clanchas.clanchas.repository;

import com.clanchas.clanchas.model.Renta;

import java.util.Optional;

public interface RentaRepository<T> extends BaseRepository<Renta> {
    Optional<T> updateUso(Long id, boolean uso);
}
