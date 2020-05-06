package com.clanchas.clanchas.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Clase donde se guardan los Usos de cada Renta
 */
public class Uso extends BaseClass {
    /**
     * El id de la renta / lancha_rentada_id
     */
    @NotNull
    @Positive
    private Long renta_id;
    /**
     * Tiempo del Uso
     *
     * De cuanto tiempo fue el uso
     */
    @NotNull
    @Size(max = 10, message = "El tiempo debe cumplir con el formato: '00:00:00'")
    private String tiempo;
    /**
     * Precio del Uso
     */
    @NotNull
    @PositiveOrZero
    private Double precio;
    /**
     * Renta a la que hace referencia renta_id
     */
    @Nullable
    private Renta renta;

    public Uso() { }

    public Uso(Long id, @NotNull @Positive Long renta_id, @NotNull @Size(max = 10, message = "El tiempo debe cumplir con el formato: '00:00:00'") String tiempo, @NotNull @PositiveOrZero Double precio) {
        super(id);
        this.renta_id = renta_id;
        this.tiempo = tiempo;
        this.precio = precio;
    }

    public Uso(@NotNull @Positive Long renta_id, @NotNull @Size(max = 10, message = "El tiempo debe cumplir con el formato: '00:00:00'") String tiempo, @NotNull @PositiveOrZero Double precio) {
        this.renta_id = renta_id;
        this.tiempo = tiempo;
        this.precio = precio;
    }

    public Long getRenta_id() {
        return renta_id;
    }

    public void setRenta_id(Long renta_id) {
        this.renta_id = renta_id;
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

    @Nullable
    public Renta getRenta() {
        return renta;
    }

    public void setRenta(@Nullable Renta renta) {
        this.renta = renta;
    }
}
