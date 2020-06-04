package com.clanchas.clanchas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que guarda a cada persona de la lista de espera
 */
public class Persona extends BaseClass {
    /**
     * Nombre de la persona en la lista de espera
     */
    @NotNull
    @Size(max = 40)
    private String nombre;

    public Persona(Long id, @NotNull @Size(max = 40) String nombre) {
        super(id);
        this.nombre = nombre;
    }

    public Persona(@NotNull @Size(max = 40) String nombre) {
        this.nombre = nombre;
    }

    public Persona() { }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
