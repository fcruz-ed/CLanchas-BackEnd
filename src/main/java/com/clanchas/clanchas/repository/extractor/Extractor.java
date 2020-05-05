package com.clanchas.clanchas.repository.extractor;

import com.clanchas.clanchas.mapper.DiarioMapper;
import com.clanchas.clanchas.mapper.RentaMapper;
import com.clanchas.clanchas.model.Diario;
import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.model.Precio;
import com.clanchas.clanchas.mapper.LanchaMapper;
import com.clanchas.clanchas.mapper.PrecioMapper;
import com.clanchas.clanchas.model.Renta;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class Extractor {

    public static final ResultSetExtractor<Optional<Lancha>> EXTRACTOR_LANCHA = singletonOptionalExtractor(new LanchaMapper());
    public static final ResultSetExtractor<Optional<Precio>> EXTRACTOR_PRECIO = singletonOptionalExtractor(new PrecioMapper());
    public static final ResultSetExtractor<Optional<Diario>> EXTRACTOR_DIARIO = singletonOptionalExtractor(new DiarioMapper());
    public static final ResultSetExtractor<Optional<Renta>> EXTRACTOR_RENTA = singletonOptionalExtractor(new RentaMapper());

    private static <T> ResultSetExtractor<Optional<T>> singletonOptionalExtractor(RowMapper<? extends T> mapper) {
        return resultSet -> resultSet.next() ? Optional.ofNullable(mapper.mapRow(resultSet, 1)) : Optional.empty();
    }
}
