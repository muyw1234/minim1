package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

public class BibliotecaManagerImpl implements BibliotecaManager {

    final static Logger logger = Logger.getLogger(BibliotecaManagerImpl.class);
    private static BibliotecaManagerImpl instance;


    public static BibliotecaManagerImpl getInstance() {
        if (instance == null) {
            instance = new BibliotecaManagerImpl();
        }
        return instance;
    }

    //key: dni
    Map<String, Lector> Maplectors = new HashMap();
    List<Prestar> listprestars = new ArrayList<>();
    //key: titulo del libro
    Map<String, LibrosCatalogado> MapLibrosCatalogado = new HashMap<>();
    List<MontonDeLibros> listMontonDeLibros = new ArrayList<>();

    private BibliotecaManagerImpl() {}



    @Override
    public Lector addLector(String nombrelector, String apellido, String dni, String fechaNacimiento, String ligarNacimiento, String emai) {
        logger.info("addLector");
        Lector l = new Lector(nombrelector, apellido, dni, fechaNacimiento, ligarNacimiento, emai);
        Maplectors.put(dni, l);
        logger.info("addedLector: " + l);
        return l;
    }


    @Override
    public Libro addLibro(String ISBN, String titulo, String editorial, String añoPublicacion, String numeroEdicion, String autor, String tematica) {
        logger.info("addLibro - parámetros: " + ISBN + ", " + titulo + ", " + editorial);

        Libro newLibro = new Libro(ISBN, titulo, editorial, añoPublicacion, numeroEdicion, autor, tematica);

        if (listMontonDeLibros.isEmpty() || listMontonDeLibros.get(listMontonDeLibros.size() - 1).estaLleno())
            listMontonDeLibros.add(new MontonDeLibros());

        listMontonDeLibros.get(listMontonDeLibros.size() - 1).apilar(newLibro);
        return newLibro;
    }

    public LibrosCatalogado catalogar() {
        logger.info("catalogar libros");

        if (listMontonDeLibros.isEmpty()) throw new RuntimeException("No hay montones para catalogar.");

        MontonDeLibros primero = listMontonDeLibros.get(0);
        Libro libro = primero.desapilar();

        if (primero.estaVacio()) listMontonDeLibros.remove(0);
        if (libro == null) throw new RuntimeException("No hay libros que catalogar.");

        LibrosCatalogado existente = MapLibrosCatalogado.get(libro.getTitulo());
        if (existente != null) existente.incrementar();
        else MapLibrosCatalogado.put(libro.getTitulo(), new LibrosCatalogado(libro));

        return MapLibrosCatalogado.get(libro.getTitulo());
    }

    @Override
    public Prestar prestarLibro(String titulo, String dni, String dataDeDevolucion) {
        logger.info("prestarLibro: " + titulo + " -> " + dni);

        if (!Maplectors.containsKey(dni)) throw new RuntimeException("Lector no existe");

        LibrosCatalogado l = MapLibrosCatalogado.values().stream()
                .filter(c -> c.getLibro().getTitulo().equals(titulo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Libro no existe"));

        if (l.getCantidad() <= 0) throw new RuntimeException("Sin ejemplares disponibles");

        l.decrementar();
        Prestar p = new Prestar(titulo, dni, dataDeDevolucion);
        listprestars.add(p);
        return p;
    }

    @Override
    public List<Prestar> consultarPrestaciones(String dni) {
        logger.info("consultarPrestaciones de " + dni);
        List<Prestar> res = new ArrayList<>();
        for (Prestar p : listprestars) {
            if (p.getLectordni().equals(dni)) res.add(p);
        }
        return res;
    }
}
