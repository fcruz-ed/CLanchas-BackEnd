package com.clanchas.clanchas.mapper;

import com.clanchas.clanchas.model.Lancha;
import com.clanchas.clanchas.model.Renta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentaMapper implements RowMapper<Renta> {
    @Override
    public Renta mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Renta(
                resultSet.getLong("id"),
                resultSet.getLong("lancha_id"),
                resultSet.getTimestamp("renta_de"),
                resultSet.getString("fecha"),
                resultSet.getBoolean("en_uso"),
                resultSet.getInt("c_adultos"),
                resultSet.getInt("c_jovenes"),
                resultSet.getString("observaciones")
        );
    }
}
