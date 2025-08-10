package com.clanchas.clanchas.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Precio extends BaseClass {
    /**
     * tiempo del Tabulador de Precios
     */
    @NotNull
    @Size(max = 10)
    private String tiempo;
    /**
     * precio por el tiempo
     */
    @NotNull
    @PositiveOrZero
    private Double precio;
    /**
     * estado del precio
     * 0 -> Desactivo
     * 1 -> Activo / Default
     */
    @Nullable
    private boolean estado;
    /**
     * fecha en la que se puso el precio
     */
    @NotNull
    @Size(max = 10)
    private String fecha;

    public Precio() {
    }

    public Precio(String tiempo, Double precio, boolean estado) {
        this.tiempo = tiempo;
        this.precio = precio;
        this.estado = estado;
    }

    public Precio(Long id, String tiempo, Double precio, boolean estado) {
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Precio{" +
                "tiempo='" + tiempo + '\'' +
                ", precio=" + precio +
                ", estado=" + estado +
                ", fecha='" + fecha + '\'' +
                ", id=" + id +
                '}';
    }
}
