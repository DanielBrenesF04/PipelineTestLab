package com.cenfotec.lab5.lab5.domain;

import javax.persistence.Entity;


public class Membresia {

    private Long id;
    private String NombreDuenno;
    private String Cedula;
    private int PorcentajeDescuento;
    private int NivelMiembro;
    private int Puntos;

    public Membresia() {
    }

    public Membresia(Long id, String nombreDuenno, String cedula, int porcentajeDescuento, int nivelMiembro, int puntos) {
        this.id = id;
        NombreDuenno = nombreDuenno;
        Cedula = cedula;
        PorcentajeDescuento = porcentajeDescuento;
        NivelMiembro = nivelMiembro;
        this.Puntos = puntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDuenno() {
        return NombreDuenno;
    }

    public void setNombreDuenno(String nombreDuenno) {
        NombreDuenno = nombreDuenno;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public int getPorcentajeDescuento() {
        return PorcentajeDescuento;
    }

    public void setPorcentajeDescuento(int porcentajeDescuento) {
        PorcentajeDescuento = porcentajeDescuento;
    }

    public int getNivelMiembro() {
        return NivelMiembro;
    }

    public void setNivelMiembro(int nivelMiembro) {
        NivelMiembro = nivelMiembro;
    }

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int puntos) {
        this.Puntos = puntos;
    }

    @Override
    public String toString() {
        return "domain.Membresia{" +
                "id=" + id +
                ", NombreDuenno='" + NombreDuenno + '\'' +
                ", Cedula='" + Cedula + '\'' +
                ", PorcentajeDescuento=" + PorcentajeDescuento +
                ", NivelMiembro=" + NivelMiembro +
                ", puntos=" + Puntos +
                '}';
    }
}