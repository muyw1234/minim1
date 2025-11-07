package edu.upc.dsa.models;

import java.util.List;

public class MontonDeLibros {
    String montonId;
    List<Libro> Montonlibros;

    public MontonDeLibros(  List<Libro> Montonlibros) {
        this.Montonlibros = Montonlibros;
    }

    public String getMontonId() {
        return montonId;
    }
    public void setMontonId(String montonId) {
        this.montonId = montonId;
    }
    public List<Libro> getMontonlibros() {
        return Montonlibros;
    }
    public Integer getNumerosLibrosEnMonton(){
        return Montonlibros.size();
    }
    public void setMontonlibros(List<Libro> Montonlibros) {
        this.Montonlibros = Montonlibros;
    }
}
