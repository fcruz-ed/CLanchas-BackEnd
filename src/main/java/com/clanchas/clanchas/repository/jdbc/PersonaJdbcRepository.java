package com.clanchas.clanchas.repository.jdbc;

import com.clanchas.clanchas.mapper.PersonaMapper;
import com.clanchas.clanchas.model.Persona;
import com.clanchas.clanchas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static com.clanchas.clanchas.repository.extractor.Extractor.EXTRACTOR_PERSONA;
import static com.clanchas.clanchas.repository.parameter.CustomSqlParameterSource.createPersonaParameterSource;

@Repository
public class PersonaJdbcRepository implements PersonaRepository {

    private final JdbcTemplate jt;

    private final SimpleJdbcInsert personaInsert;

    @Autowired
    public PersonaJdbcRepository(DataSource dataSource, JdbcTemplate jt) {
        this.personaInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("persona_espera")
                .usingGeneratedKeyColumns("id");
        this.jt = jt;
    }

    @Override
    public Optional<Persona> save(Persona persona) {
        Number newKey = personaInsert.executeAndReturnKey(createPersonaParameterSource(persona));
        persona.setId(newKey.longValue());
        return Optional.of(persona);
    }

    @Override
    public List<Persona> findAll() {
        return jt.query("select * from persona_espera;", new PersonaMapper());
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return jt.query("select * from persona_espera where id=?;", EXTRACTOR_PERSONA, id);
    }

    @Override
    public void deleteById(Long id) {
        jt.update("delete from persona_espera where id=?;", id);
    }

    @Override
    public Optional<Persona> update(Persona persona) {
        jt.update("update persona_espera set nombre=? where id=?;", persona.getNombre(), persona.getId());
        return this.findById(persona.getId());
    }
}
