package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface BibliotecaManager {
    //añadir lector
    public Lector addLector(String nombrelector, String apellido, String dni,
                            String fechaNacimiento,String lugarNacimiento,
                            String emai);

    //libro
    public Libro addLibro(String ISBN, String titulo, String editorial,
                          String anoPublicacion, String numeroEdicion,
                          String autor, String tematica);

    //public MontonDeLibros añadirAlMonton (List<Libro> libros);
    public LibrosCatalogado catalogar ();

    //prestar
    public Prestar prestarLibro(String titulo, String dni, String dataDeDevolucion);


    public List<Prestar> consultarPrestaciones (String dni);
}
