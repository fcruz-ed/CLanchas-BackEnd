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

@Repository
public class DiarioJdbcRepository implements DiarioRepository {

    @Autowired
    private JdbcTemplate jt;

    private final SimpleJdbcInsert diarioInsert;

    @Autowired
    public DiarioJdbcRepository(DataSource dataSource) {
        this.diarioInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("diario")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Diario> save(Diario diario) {
        if(obtenerDiarioConFecha(diario.getDia()).equals(0)) {
            Number number = diarioInsert.executeAndReturnKey(CustomSqlParameterSource.createDiarioParameterSource(diario));
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
    public void update(Diario diario) {
        jt.update("update diario set descripcion=? where id=?;", diario.getDescripcion(), diario.getId());
    }

    @Override
    public Optional<Diario> findByDay(Date date) {
        return jt.query("select * from diario where dia=?", EXTRACTOR_DIARIO, date);
    }

    private Integer obtenerDiarioConFecha(Date date) {
        return jt.queryForObject("select count(*) from diario where dia=?;", Integer.class, date);
    }
}
