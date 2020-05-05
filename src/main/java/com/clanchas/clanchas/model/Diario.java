package com.clanchas.clanchas.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class Diario extends BaseClass {
    /**
     * Día en que se hizo el diario
     */
    private Date dia;
    /**
     * Descripción del día
     */
    @NotNull
    @Max(500)
    private String Descripcion;

    public Diario(long id, Timestamp dia, String descripcion) { }

    public Diario(String descripcion) {
        this.dia = new Date();
        Descripcion = descripcion;
    }

    public Diario(Long id, String descripcion) {
        super(id);
        this.dia = new Date();
        Descripcion = descripcion;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Timestamp dia) {
        this.dia = dia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
