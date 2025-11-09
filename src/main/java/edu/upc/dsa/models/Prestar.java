package edu.upc.dsa.models;

import java.time.LocalDate;
import java.util.UUID;

public class Prestar {
    String prestarId;
    String lectordni;
    String libroTitulo;
    LocalDate dataDePrestar;
    String dataDeDevolucion;
    String estado;
    public Prestar( String libroTitulo, String lectordni ,
                    String dataDeDevolucion) {
        this.prestarId = UUID.randomUUID().toString();
        this.lectordni = lectordni;
        this.libroTitulo = libroTitulo;
        this.dataDePrestar = LocalDate.now();
        this.dataDeDevolucion = dataDeDevolucion;
        this.estado = "En tramite";
    }

    public Prestar() {}

    public String getPrestarId() {
        return prestarId;
    }
    public void setPrestarId(String prestarId) {
        this.prestarId = prestarId;
    }
    public String getLectordni() {
        return lectordni;
    }
    public void setLectordni(String lectordni) {
        this.lectordni = lectordni;
    }
    public String getLibroTitulo() {
        return libroTitulo;
    }
    public void setLibroTitulo(String libroTitulo) {
        this.libroTitulo = libroTitulo;
    }
    public LocalDate getDataDePrestar() {
        return dataDePrestar;
    }
    public void setDataDePrestar(LocalDate dataDePrestar) {
        this.dataDePrestar = dataDePrestar;
    }
    public String getDataDeDevolucion() {
        return dataDeDevolucion;
    }
    public void setDataDeDevolucion(String dataDeDevolucion) {
        this.dataDeDevolucion = dataDeDevolucion;
    }
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
