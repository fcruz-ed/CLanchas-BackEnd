package com.clanchas.clanchas.mapper;

import com.clanchas.clanchas.model.Persona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaMapper implements RowMapper<Persona> {
    @Override
    public Persona mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Persona(
                resultSet.getLong("id"),
                resultSet.getString("nombre"));
    }
}
