package com.clanchas.clanchas.repository.jdbc;

import com.clanchas.clanchas.mapper.UsoMapper;
import com.clanchas.clanchas.model.Renta;
import com.clanchas.clanchas.model.Uso;
import com.clanchas.clanchas.repository.UsoRepository;
import com.clanchas.clanchas.repository.extractor.Extractor;
import com.clanchas.clanchas.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource.createUsoParameterSource;

@Repository
public class UsoJdbcRepository implements UsoRepository {

    private final SimpleJdbcInsert insert;

    private final JdbcTemplate jt;

    private final RentaJdbcRepository rentaJdbcRepository;

    @Autowired
    public UsoJdbcRepository(DataSource dataSource, JdbcTemplate jt, RentaJdbcRepository rentaJdbcRepository) {
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("usos")
                .usingGeneratedKeyColumns("id");
        this.jt = jt;
        this.rentaJdbcRepository = rentaJdbcRepository;
    }

    @Override
    public Optional<Uso> save(Uso uso) {
        Number newKey = insert.executeAndReturnKey(createUsoParameterSource(uso));
        return this.findById(newKey.longValue());
    }

    @Override
    public List<Uso> findAll() {
        List<Uso> usos = jt.query("select * from usos;", new UsoMapper());
        List<Renta> rentas = rentaJdbcRepository.findAll();
        for(Uso uso: usos) {
            uso.setRenta(EntityUtils.getById(rentas, Renta.class, uso.getRenta_id()));
        }
        return usos;
    }

    @Override
    public Optional<Uso> findById(Long id) {
        Optional<Uso> usoOptional = jt.query("select * from usos where id=?;", Extractor.EXTRACTOR_USO, id);
        if (usoOptional != null && usoOptional.isPresent()) {
            List<Renta> rentas = rentaJdbcRepository.findAll();
            usoOptional.get().setRenta(EntityUtils.getById(rentas, Renta.class, usoOptional.get().getRenta_id()));
        }
        return usoOptional;
    }

    @Override
    public void deleteById(Long id) {
        jt.update("delete from usos where id=?;", id);
    }

    @Override
    public Optional<Uso> update(Uso uso) {
        jt.update("update usos set lancha_rentada_id=?, tiempo=?, precio=? where id=?",
                uso.getRenta_id(), uso.getTiempo(), uso.getPrecio(), uso.getId());
        return this.findById(uso.getId());
    }
}
