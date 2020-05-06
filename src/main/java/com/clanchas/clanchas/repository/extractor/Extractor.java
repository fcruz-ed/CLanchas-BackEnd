package com.clanchas.clanchas.repository.extractor;

import com.clanchas.clanchas.mapper.*;
import com.clanchas.clanchas.model.*;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public abstract class Extractor {

    public static final ResultSetExtractor<Optional<Lancha>> EXTRACTOR_LANCHA = singletonOptionalExtractor(new LanchaMapper());
    public static final ResultSetExtractor<Optional<Precio>> EXTRACTOR_PRECIO = singletonOptionalExtractor(new PrecioMapper());
    public static final ResultSetExtractor<Optional<Diario>> EXTRACTOR_DIARIO = singletonOptionalExtractor(new DiarioMapper());
    public static final ResultSetExtractor<Optional<Renta>> EXTRACTOR_RENTA = singletonOptionalExtractor(new RentaMapper());
    public static final ResultSetExtractor<Optional<Uso>> EXTRACTOR_USO = singletonOptionalExtractor(new UsoMapper());

    private static <T> ResultSetExtractor<Optional<T>> singletonOptionalExtractor(RowMapper<? extends T> mapper) {
        return resultSet -> resultSet.next() ? Optional.ofNullable(mapper.mapRow(resultSet, 1)) : Optional.empty();
    }
}
