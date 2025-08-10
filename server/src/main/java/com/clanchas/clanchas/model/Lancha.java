package com.clanchas.clanchas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class Lancha extends BaseClass {
    /**
     * Número asignado a la Lancha
     * p.e. 1, 2, 3
     */
    @NotNull
    @Positive
    private int numero;
    /**
     * Nombre de la Lancha
     */
    @NotNull
    private String nombre;
    /**
     * Estado de la Lancha
     * 0 -> Disponible
     * 1 -> Ocupada
     * 2 -> En reparación
     */
    @NotNull
    @PositiveOrZero
    private short estado;

    public Lancha() { }

    public Lancha(int numero, String nombre, short estado) {
        this.numero = numero;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Lancha(Long id, int numero, String nombre, short estado) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Lancha{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                ", id=" + id +
                '}';
    }
}
