package com.clanchas.clanchas.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

public class Precio extends BaseClass {
    /**
     * tiempo del Tabulador de Precios
     */
    @NotNull
    private String tiempo;
    /**
     * precio por el tiempo
     */
    @NotNull
    @PositiveOrZero
    private Double precio;
    /**
     * estado del precio
     * 0 -> Activo
     * 1 -> Desactivo
     */
    @NotNull
    @PositiveOrZero
    private short estado;
    /**
     * fecha en la que se puso el precio
     */
    @Nullable
    private Date fecha = new Date();

    public Precio() {
    }

    public Precio(String tiempo, Double precio, short estado) {
        this.tiempo = tiempo;
        this.precio = precio;
        this.estado = estado;
    }

    public Precio(Long id, String tiempo, Double precio, short estado) {
        this.id = id;
        this.tiempo = tiempo;
        this.precio = precio;
        this.estado = estado;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
