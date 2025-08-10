package com.clanchas.clanchas.mapper;

import com.clanchas.clanchas.model.Lancha;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanchaMapper implements RowMapper<Lancha> {

    @Override
    public Lancha mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Lancha(
                resultSet.getLong("id"),
                resultSet.getInt("numero"),
                resultSet.getString("nombre"),
                resultSet.getShort("estado")
        );
    }



}
