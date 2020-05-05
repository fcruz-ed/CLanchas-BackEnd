package com.clanchas.clanchas.repository.jdbc;

import com.clanchas.clanchas.mapper.RentaMapper;
import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.model.Renta;
import com.clanchas.clanchas.repository.LanchaRepository;
import com.clanchas.clanchas.repository.RentaRepository;
import com.clanchas.clanchas.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static com.clanchas.clanchas.repository.extractor.Extractor.EXTRACTOR_RENTA;
import static com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource.createRentaParameterSource;

// TODO: hacer que el save y update regresen por defecto la renta actualizada
@Repository
public class RentaJdbcRepository implements RentaRepository {

    @Autowired
    private JdbcTemplate jt;

    private final SimpleJdbcInsert rentaInsert;

    private final LanchaRepository lanchaRepository;

    @Autowired
    public RentaJdbcRepository(DataSource dataSource, LanchaRepository lanchaRepository) {
        this.rentaInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("lancha_rentada")
                .usingGeneratedKeyColumns("id");
        this.lanchaRepository = lanchaRepository;
    }

    @Override
    public Optional<Renta> save(Renta renta) {
        Number newKey = this.rentaInsert.executeAndReturnKey(createRentaParameterSource(renta));
        renta.setId(newKey.longValue());
        return Optional.of(renta);
    }

    @Override
    public List<Renta> findAll() {
        List<Renta> rentas = jt.query("select * from lancha_rentada;", new RentaMapper());
        List<Lancha> lanchas = lanchaRepository.findAll();
        for (Renta renta: rentas) {
            renta.setLancha(EntityUtils.getById(lanchas, Lancha.class, renta.getLancha_id()));
        }
        return rentas;
        //return jt.query("select * from lancha_rentada;", new RentaMapper());
    }

    @Override
    public Optional<Renta> findById(Long id) {
        Optional<Renta> renta = jt.query("select * from lancha_rentada where id=?;", EXTRACTOR_RENTA, id);
        List<Lancha> lanchas = lanchaRepository.findAll();
        if (renta != null && renta.isPresent()) {
            renta.get().setLancha(EntityUtils.getById(lanchas, Lancha.class, renta.get().getLancha_id()));
        }
        return renta;
        //return jt.query("select * from lancha_rentada where id=?;", EXTRACTOR_RENTA, id);
    }

    @Override
    public void deleteById(Long id) {
        jt.update("delete from lancha_rentada where id=?", id);
    }

    @Override
    public void update(Renta renta) {
        jt.update("update lancha_rentada set lancha_id=?, en_uso=?, c_adultos=?, c_jovenes=?, observaciones=? where id=?;",
                renta.getLancha_id(), renta.isEn_uso(), renta.getC_adultos(), renta.getC_jovenes(), renta.getObservaciones(),
                renta.getId());
    }

    @Override
    public void updateUso(Long id, boolean uso) {
        jt.update("update lancha_rentada set en_uso=? where id=?", uso, id);
    }
}
