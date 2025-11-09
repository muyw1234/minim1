package edu.upc.dsa.models;

public class LibrosCatalogado {
    int cantidad;
    Libro libro;

    public LibrosCatalogado(Libro libro) {
        this.cantidad = 1;
        this.libro = libro;
    }

    public LibrosCatalogado() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = this.cantidad + cantidad;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void incrementar() { this.cantidad++; }
    public void decrementar() { this.cantidad--; }
}
