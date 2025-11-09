package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class MontonDeLibros {
    String montonId;
    List<Libro> Montonlibros;

    public MontonDeLibros() {
        this.Montonlibros = new LinkedList<>();
        this.montonId = UUID.randomUUID().toString();

    }

    public void apilar(Libro libro) {
        Montonlibros.add(libro);
    }

    public Libro desapilar() {
        if (Montonlibros.isEmpty()) return null;
        return Montonlibros.remove(Montonlibros.size() - 1);
    }

    public boolean estaLleno() {
        return Montonlibros.size() >= 10;
    }

    public boolean estaVacio() {
        return Montonlibros.isEmpty();
    }

    public List<Libro> getLibros() {
        return Montonlibros;
    }
}
