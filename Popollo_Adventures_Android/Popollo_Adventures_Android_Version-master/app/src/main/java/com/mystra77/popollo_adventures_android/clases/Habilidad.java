package com.mystra77.popollo_adventures_android.clases;


import java.io.Serializable;

public class Habilidad implements Serializable {
    private String nombre;
    private int poder;
    private int coste;
    private Boolean ofensivo;

    public Habilidad(String nombre, int poder, int coste, Boolean ofensivo) {
        this.nombre = nombre;
        this.poder = poder;
        this.coste = coste;
        this.ofensivo = ofensivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public Boolean getOfensivo() {
        return ofensivo;
    }

    /**
     * True = habilidad ofensiva - False = habilidad curativa
     * @param ofensivo
     */
    public void setOfensivo(Boolean ofensivo) {
        this.ofensivo = ofensivo;
    }
}
