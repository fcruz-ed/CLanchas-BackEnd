package com.clanchas.clanchas.repository.jdbc;

import com.clanchas.clanchas.model.Precio;
import com.clanchas.clanchas.mapper.PrecioMapper;
import com.clanchas.clanchas.repository.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static com.clanchas.clanchas.repository.extractor.Extractor.EXTRACTOR_PRECIO;
import static com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource.*;

@Repository
public class PrecioJdbcRepository implements PrecioRepository {

    private final JdbcTemplate jt;

    private final SimpleJdbcInsert precioInsert;

    @Autowired
    public PrecioJdbcRepository(DataSource dataSource, JdbcTemplate jt) {
        this.precioInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tabulador_precios")
                .usingGeneratedKeyColumns("id");
        this.jt = jt;
    }

    @Override
    public Optional<Precio> save(Precio precio) {
        Number newKey = precioInsert.executeAndReturnKey(createPrecioParameterSource(precio));
        precio.setId(newKey.longValue());
        return Optional.of(precio);
    }

    @Override
    public List<Precio> findAll() {
        return jt.query("select * from tabulador_precios;", new PrecioMapper());
    }

    public List<Precio> findPreciosActivos() {
        List<Precio> precios = this.findAll();
        precios.removeIf(precio -> !precio.getEstado());
        return precios;
    }

    @Override
    public Optional<Precio> findById(Long id) {
        return jt.query("select * from tabulador_precios where id=?;", EXTRACTOR_PRECIO, id);
    }

    @Override
    public Optional<Precio> update(Precio precio) {
        jt.update("update tabulador_precios set tiempo=?, precio=?, estado=? where id=?;", precio.getTiempo(),
                precio.getPrecio(), precio.getEstado(), precio.getId());
        return this.findById(precio.getId());
    }

    @Override
    public void deleteById(Long id) {
        jt.update("delete from tabulador_precios where id=?;", id);
    }
}
