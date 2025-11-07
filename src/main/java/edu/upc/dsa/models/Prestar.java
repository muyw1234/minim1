package edu.upc.dsa.models;

import java.time.LocalDate;
import java.util.UUID;

public class Prestar {
    String prestarId;
    String lectordni;
    Libro libro;
    LocalDate dataDePrestar;
    String dataDeDevolucion;

    public Prestar( String lectorId, Libro libroId,
                   LocalDate dataDePrestar, String dataDeDevolucion) {
        this.prestarId = UUID.randomUUID().toString();
        this.lectordni = lectorId;
        this.libro = libroId;
        this.dataDePrestar = dataDePrestar;
        this.dataDeDevolucion = dataDeDevolucion;
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
    public Libro getLibroId() {
        return libro;
    }
    public void setLibroId(Libro libroId) {
        this.libro = libroId;
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
}
