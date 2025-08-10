package com.clanchas.clanchas.mapper;

import com.clanchas.clanchas.model.Uso;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsoMapper implements RowMapper<Uso> {
    @Override
    public Uso mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Uso(
                resultSet.getLong("id"),
                resultSet.getLong("lancha_rentada_id"),
                resultSet.getString("tiempo"),
                resultSet.getDouble("precio")
        );
    }
}
