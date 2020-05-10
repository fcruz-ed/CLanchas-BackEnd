package com.clanchas.clanchas.repository.jdbc;

import com.clanchas.clanchas.mapper.DiarioMapper;
import com.clanchas.clanchas.model.Diario;
import com.clanchas.clanchas.repository.DiarioRepository;
import com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.clanchas.clanchas.repository.extractor.Extractor.EXTRACTOR_DIARIO;
import static com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource.*;

@Repository
public class DiarioJdbcRepository implements DiarioRepository {

    private final JdbcTemplate jt;

    private final SimpleJdbcInsert diarioInsert;

    @Autowired
    public DiarioJdbcRepository(DataSource dataSource, JdbcTemplate jt) {
        this.diarioInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("diario")
                .usingGeneratedKeyColumns("id");
        this.jt = jt;
    }

    @Override
    public Optional<Diario> save(Diario diario) {
        if(obtenerDiarioConFecha(diario.getDia()).equals(0)) {
            Number number = diarioInsert.executeAndReturnKey(createDiarioParameterSource(diario));
            diario.setId(number.longValue());
            return Optional.of(diario);
        }
        return Optional.empty();
    }

    @Override
    public List<Diario> findAll() {
        return jt.query("select * from diario", new DiarioMapper());
    }

    @Override
    public Optional<Diario> findById(Long id) {
        return jt.query("select * from diario where id=?;", EXTRACTOR_DIARIO, id);
    }

    @Override
    public void deleteById(Long id) {
        jt.update("delete from diario where id=?;", id);
    }

    @Override
    public Optional<Diario> update(Diario diario) {
        jt.update("update diario set dia=?, descripcion=? where id=?;",
                diario.getDia(), diario.getDescripcion(), diario.getId());
        return this.findById(diario.getId());
    }

    @Override
    public Optional<Diario> findByDay(Date date) {
        return jt.query("select * from diario where dia=?", EXTRACTOR_DIARIO, date);
    }

    private Integer obtenerDiarioConFecha(String date) {
        return jt.queryForObject("select count(*) from diario where dia=?;", Integer.class, date);
    }
}
