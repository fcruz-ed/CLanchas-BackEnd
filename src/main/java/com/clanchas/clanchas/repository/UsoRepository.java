package com.clanchas.clanchas.repository;

import com.clanchas.clanchas.model.Uso;

import java.util.List;

public interface UsoRepository extends BaseRepository<Uso> {
    List<Uso> findUsosDeRentaActiva();
}
