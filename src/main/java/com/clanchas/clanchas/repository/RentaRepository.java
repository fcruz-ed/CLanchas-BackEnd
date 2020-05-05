package com.clanchas.clanchas.repository;

import com.clanchas.clanchas.model.Renta;

public interface RentaRepository extends BaseRepository<Renta> {
    void updateUso(Long id, boolean uso);
}
