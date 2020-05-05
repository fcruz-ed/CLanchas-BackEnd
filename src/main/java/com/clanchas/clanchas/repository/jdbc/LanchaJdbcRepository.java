package com.clanchas.clanchas.repository.jdbc;

import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.mapper.LanchaMapper;
import com.clanchas.clanchas.repository.LanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static com.clanchas.clanchas.repository.extractor.Extractor.EXTRACTOR_LANCHA;
import static com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource.createLanchaParameterSource;

@Repository
public class LanchaJdbcRepository implements LanchaRepository {

    @Autowired
    private JdbcTemplate jt;

    private final SimpleJdbcInsert lanchaInsert;

    @Autowired
    public LanchaJdbcRepository(DataSource dataSource) {
        this.lanchaInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("lancha")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Lancha> save(Lancha obj) {
        if(obtenerLanchasConNumero(obj.getNumero()) == 0) {
            Number newKey = this.lanchaInsert.executeAndReturnKey(createLanchaParameterSource(obj));
            obj.setId(newKey.longValue());
            return Optional.of(obj);
        }
        return Optional.empty();
    }

    @Override
    public List<Lancha> findAll() {
        return jt.query("select * from lancha;", new LanchaMapper());
    }

    @Override
    public Optional<Lancha> findById(Long id) {
        return jt.query("select * from lancha where id=?;", EXTRACTOR_LANCHA, id);
    }

    @Override
    public void deleteById(Long id) {
        jt.update("delete from lancha where id=?;", id);
    }

    @Override
    public Optional<Lancha> update(Lancha lancha) {
        jt.update("update lancha set nombre=?, estado=? where id=?;",
                lancha.getNombre(), lancha.getEstado(), lancha.getId());
        return this.findById(lancha.getId());
    }

    private Integer obtenerLanchasConNumero(int numero) {
        return jt.queryForObject("select count(*) from lancha where numero=?;", Integer.class, numero);
    }
}
