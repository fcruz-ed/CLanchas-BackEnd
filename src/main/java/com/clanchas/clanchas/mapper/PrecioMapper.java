package com.clanchas.clanchas.mapper;

import com.clanchas.clanchas.model.Precio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrecioMapper implements RowMapper<Precio> {
    @Override
    public Precio mapRow(ResultSet resultSet, int i) throws SQLException {
        Precio precio = new Precio();
        precio.setId(resultSet.getLong("id"));
        precio.setTiempo(resultSet.getString("tiempo"));
        precio.setPrecio(resultSet.getDouble("precio"));
        precio.setEstado(resultSet.getShort("estado"));
        precio.setFecha(resultSet.getDate("fecha"));
        return precio;
    }
}
