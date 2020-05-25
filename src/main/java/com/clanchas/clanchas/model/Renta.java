package com.clanchas.clanchas.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;

// TODO: Se puede agregar campos para algún dato del cliente
// esto por si le pasa algo a la lancha poder contactarlo de algúna manera
// y claro saber al menos el nombre y/o número de el responsable que se haya subido
/**
 * Clase modelo para la tabla "lancha_rentada" de la base de datos.
 */
public class Renta extends BaseClass {
    /**
     * id de la Lancha a la que hace refenrencia.
     */
    @NotNull
    @Positive
    private Long lancha_id;
    /**
     * Fecha y hora en la que comenzó la renta.
     */
    @Nullable
    private Timestamp renta_de = new Timestamp(new Date().getTime());
    /**
     * Fecha de la renta.
     */
    @NotNull
    @Size(max = 10)
    private String fecha;
    /**
     * Muestra si está en uso o no la lancha.
     * False / 0 -> No uso
     * True / 1 -> En uso
     */
    private boolean en_uso = true;
    /**
     * Cantidad de adultos que iban en la lancha
     */
    @PositiveOrZero
    @NotNull
    private int c_adultos;
    /**
     * Cantidad de jovenes que iban en la lancha
     */
    @PositiveOrZero
    @NotNull
    private int c_jovenes;
    /**
     * Observaciones que se pueden añadir al terminar la renta, p.e:
     * "Las personas que se subieron a la Lancha la dañaron."
     * "Se le acabó la gasolina a la Lancha", etc.
     */
    @Size(max = 500, message = "Las observaciones no deben pasar de 500 caracteres.")
    private String observaciones = "";

    @Nullable
    private Lancha lancha;

    public Renta() { }

    public Renta(Long lancha_id, Timestamp renta_de, String fecha, boolean en_uso, int c_adultos, int c_jovenes, String observaciones) {
        this.lancha_id = lancha_id;
        this.renta_de = renta_de;
        this.fecha = fecha;
        this.en_uso = en_uso;
        this.c_adultos = c_adultos;
        this.c_jovenes = c_jovenes;
        this.observaciones = observaciones;
    }

    public Renta(Long id, Long lancha_id, Timestamp renta_de, String fecha, boolean en_uso, int c_adultos, int c_jovenes, String observaciones) {
        super(id);
        this.lancha_id = lancha_id;
        this.renta_de = renta_de;
        this.fecha = fecha;
        this.en_uso = en_uso;
        this.c_adultos = c_adultos;
        this.c_jovenes = c_jovenes;
        this.observaciones = observaciones;
    }

    public Long getLancha_id() {
        return lancha_id;
    }

    public void setLancha_id(Long lancha_id) {
        this.lancha_id = lancha_id;
    }

    public Timestamp getRenta_de() {
        return renta_de;
    }

    public void setRenta_de(Timestamp renta_de) {
        this.renta_de = renta_de;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isEn_uso() {
        return en_uso;
    }

    public void setEn_uso(boolean en_uso) {
        this.en_uso = en_uso;
    }

    public int getC_adultos() {
        return c_adultos;
    }

    public void setC_adultos(int c_adultos) {
        this.c_adultos = c_adultos;
    }

    public int getC_jovenes() {
        return c_jovenes;
    }

    public void setC_jovenes(int c_jovenes) {
        this.c_jovenes = c_jovenes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Nullable
    public Lancha getLancha() {
        return lancha;
    }

    public void setLancha(@Nullable Lancha lancha) {
        this.lancha = lancha;
    }

    @Override
    public String toString() {
        return "Renta{" +
                "lancha_id=" + lancha_id +
                ", renta_de=" + renta_de +
                ", fecha='" + fecha + '\'' +
                ", en_uso=" + en_uso +
                ", c_adultos=" + c_adultos +
                ", c_jovenes=" + c_jovenes +
                ", observaciones='" + observaciones + '\'' +
                ", id=" + id +
                '}';
    }
}
