package com.clanchas.clanchas.repository.parameter;

import com.clanchas.clanchas.model.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public abstract class CustomSqlParameterSource {
    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Lancha} instance.
     */
    public static MapSqlParameterSource createLanchaParameterSource(Lancha lancha) {
        return new MapSqlParameterSource()
                .addValue("id", lancha.getId())
                .addValue("numero", lancha.getNumero())
                .addValue("nombre", lancha.getNombre())
                .addValue("estado", lancha.getEstado());
    }
    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Precio} instance.
     */
    public static MapSqlParameterSource createPrecioParameterSource(Precio precio) {
        return new MapSqlParameterSource()
                .addValue("id", precio.getId())
                .addValue("tiempo", precio.getTiempo())
                .addValue("precio", precio.getPrecio())
                .addValue("estado", precio.getEstado())
                .addValue("fecha", precio.getFecha());
    }
    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Diario} instance.
     */
    public static MapSqlParameterSource createDiarioParameterSource(Diario diario) {
        return new MapSqlParameterSource()
                .addValue("id", diario.getId())
                .addValue("dia", diario.getDia())
                .addValue("descripcion", diario.getDescripcion());
    }
    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Renta} instance.
     */
    public static MapSqlParameterSource createRentaParameterSource(Renta renta) {
        return new MapSqlParameterSource()
                .addValue("id", renta.getId())
                .addValue("lancha_id", renta.getLancha_id())
                .addValue("renta_de", renta.getRenta_de())
                .addValue("fecha", renta.getFecha())
                .addValue("en_uso", renta.isEn_uso())
                .addValue("c_adultos", renta.getC_adultos())
                .addValue("c_jovenes", renta.getC_jovenes())
                .addValue("observaciones", renta.getObservaciones());
    }
    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Uso} instance.
     */
    public static MapSqlParameterSource createUsoParameterSource(Uso uso) {
        return new MapSqlParameterSource()
                .addValue("id", uso.getId())
                .addValue("lancha_rentada_id", uso.getRenta_id())
                .addValue("tiempo", uso.getTiempo())
                .addValue("precio", uso.getPrecio());
    }
    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Persona} instance.
     */
    public static MapSqlParameterSource createPersonaParameterSource(Persona persona) {
        return new MapSqlParameterSource()
                .addValue("id", persona.getId())
                .addValue("nombre", persona.getNombre());
    }

}
