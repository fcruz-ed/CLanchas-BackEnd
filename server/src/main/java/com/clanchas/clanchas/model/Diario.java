package com.clanchas.clanchas.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

public class Diario extends BaseClass {
    /**
     * Día en que se hizo el diario
     */
    @NotNull
    @Size(max = 10)
    private String dia;
    /**
     * Descripción del día
     */
    @NotNull
    @Size(max = 500)
    private String Descripcion;

    public Diario() {
    }

    public Diario(Long id, @NotNull @Size(max = 10) String dia, @NotNull @Size(max = 500) String descripcion) {
        super(id);
        this.dia = dia;
        Descripcion = descripcion;
    }

    public Diario(@NotNull @Size(max = 10) String dia, @NotNull @Size(max = 500) String descripcion) {
        this.dia = dia;
        Descripcion = descripcion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
