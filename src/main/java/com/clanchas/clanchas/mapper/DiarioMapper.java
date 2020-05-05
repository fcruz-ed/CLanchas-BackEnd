package com.clanchas.clanchas.mapper;

import com.clanchas.clanchas.model.Diario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiarioMapper implements RowMapper<Diario> {
    @Override
    public Diario mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Diario(
                resultSet.getLong("id"),
                resultSet.getTimestamp("dia"),
                resultSet.getString("descripcion"));
    }
}
