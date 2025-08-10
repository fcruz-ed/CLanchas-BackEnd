package com.clanchas.clanchas.repository;

import com.clanchas.clanchas.model.Diario;

import java.util.Date;
import java.util.Optional;

public interface DiarioRepository extends BaseRepository<Diario> {
    Optional<Diario> findByDay(Date date);
}
