package edu.upc.dsa.models;

import java.util.List;

public class LibrosCatalogado {
    int cantidad;
    Libro libro;
    public LibrosCatalogado(int cantidad, Libro libro) {
        this.cantidad = cantidad;
        this.libro = libro;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = this.cantidad +cantidad;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
