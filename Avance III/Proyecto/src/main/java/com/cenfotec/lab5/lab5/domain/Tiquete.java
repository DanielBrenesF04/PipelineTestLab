package com.cenfotec.lab5.lab5.domain;


public class Tiquete {


    private Long id;
    private String NombrePelicula;
    private String Sala;
    private String Asiento;

    public Tiquete() {

    }

    public Tiquete(Long id, String nombrePelicula, String sala, String asiento) {
        this.id = id;
        NombrePelicula = nombrePelicula;
        Sala = sala;
        Asiento = asiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePelicula() {
        return NombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        NombrePelicula = nombrePelicula;
    }

    public String getSala() {
        return Sala;
    }

    public void setSala(String sala) {
        Sala = sala;
    }

    public String getAsiento() {
        return Asiento;
    }

    public void setAsiento(String asiento) {
        Asiento = asiento;
    }

    @Override
    public String toString() {
        return "domain.Tiquete{" +
                "id=" + id +
                ", NombrePelicula='" + NombrePelicula + '\'' +
                ", Sala='" + Sala + '\'' +
                ", Asiento='" + Asiento + '\'' +
                '}';
    }
}
