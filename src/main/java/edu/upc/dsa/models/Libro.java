package edu.upc.dsa.models;

public class Libro {
    String ISBN;
    String titulo;
    String editorial;
    String añoPublicacion;
    Integer numeroEdicion;
    String autor;
    String tematica;
    LibrosCatalogado librocatalogado;


    public Libro(String ISBN, String titulo, String editorial, String añoPublicacion,
                 Integer numeroEdicion, String autor, String tematica) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.editorial = editorial;
        this.añoPublicacion= añoPublicacion;
        this.numeroEdicion = numeroEdicion;
        this.autor = autor;
        this.tematica = tematica;
    }

    public Libro() {}

    //getter y setter
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public String getAñoPublicacion(){
        return añoPublicacion;
    }
    public void setAñoPublicacion(String año){
        this.añoPublicacion = año;
    }
    public Integer getNumeroEdicion() {
        return numeroEdicion;
    }
    public void setNumeroEdicion(Integer numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTematica() {
        return tematica;
    }
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
    public LibrosCatalogado getLibrocatalogado() {
        return librocatalogado;
    }
    public void setLibrocatalogado(LibrosCatalogado librocatalogado) {
        this.librocatalogado = librocatalogado;
    }

}
