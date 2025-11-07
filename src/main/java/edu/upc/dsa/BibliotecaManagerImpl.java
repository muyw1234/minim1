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

    //key: email
    Map<String, Lector> listlectors;
    List<Prestar> listprestars;
    Queue<Libro> listLibros;
    List<LibrosCatalogado> listLibrosCatalogado;
    //key: titulo del libro
    Map<String, LibrosCatalogado> MapLibrosCatalogado;
    List<MontonDeLibros> listMontonDeLibros;
    Map<String, List<Prestar>> MapPrestar;

    private BibliotecaManagerImpl() {
        listlectors = new HashMap<>();
        listprestars = new ArrayList<>();
        listLibros = new LinkedList<>();
        listMontonDeLibros = new ArrayList<>();
        MapLibrosCatalogado = new HashMap<>();
        listLibrosCatalogado = new ArrayList<>();
        MapPrestar = new HashMap<>();
    }

    @Override
    public Lector addLector(String nombrelector, String apellido, String dni, String fechaNacimiento, String ligarNacimiento, String emai) {
        logger.info("addLector");
        Lector l = new Lector(nombrelector, apellido, dni, fechaNacimiento, ligarNacimiento, emai);
        listlectors.put(emai, l);
        logger.info("addedLector: " + l);
        return l;
    }


    @Override
    public Libro addLibro(String ISBN, String titulo, String editorial, String añoPublicacion, String numeroEdicion, String autor, String tematica) {
        logger.info("addLibro");
        Libro newLibro = new Libro(ISBN, titulo, editorial, añoPublicacion, numeroEdicion, autor, tematica);
        listLibros.add(newLibro);
        logger.info("addLibro: " + newLibro);
        return newLibro;
    }


    @Override
    public MontonDeLibros añadirAlMonton(List<Libro> libros) {
        logger.info("añadirAlMonton");

        if (listLibros == null || listLibros.isEmpty()) {
            logger.error("⚠️ No hi ha llibres a la cua per afegir al muntó.");
            return null;
        }

        List<Libro> librosDelMonton = new ArrayList<>();

        int maxLibrosPorMonton = 10;
        for (int i = 0; i < maxLibrosPorMonton && !listLibros.isEmpty(); i++) {
            Libro libro = listLibros.poll(); // treu el primer llibre de la cua
            librosDelMonton.add(libro);
        }
        MontonDeLibros m = new MontonDeLibros(librosDelMonton);
        listMontonDeLibros.add(m);
        return m;
    }

    public List<LibrosCatalogado> catalogar(MontonDeLibros montonDeLibros) {
        logger.info("catalogar libros");

        List<LibrosCatalogado> catalogados = new ArrayList<>();

        if (listMontonDeLibros == null || listMontonDeLibros.isEmpty()) {
            logger.error("No hay montones de libros para catalogar.");
            return catalogados;
        }

        // Escoger el montón más grande
        MontonDeLibros m = listMontonDeLibros.stream()
                .max(Comparator.comparingInt(MontonDeLibros::getNumerosLibrosEnMonton))
                .orElse(null);

        if (m == null) {
            logger.error("No se encontró ningún montón válido.");
            return catalogados;
        }

        for (Libro l : m.getMontonlibros()) {
            LibrosCatalogado lc = MapLibrosCatalogado.get(l.getTitulo());

            if (lc != null) {
                // Ya existe -> sumamos 1
                lc.setCantidad(lc.getCantidad() + 1);
            } else {
                // Nuevo libro catalogado
                lc = new LibrosCatalogado(1, l);
                MapLibrosCatalogado.put(l.getTitulo(), lc);
                listLibrosCatalogado.add(lc);
            }
            catalogados.add(lc);
        }
        listMontonDeLibros.remove(m);
        logger.info("Catalogados " + catalogados.size() + " libros del montón.");
        return catalogados;
    }

    @Override
    public Prestar prestarLibro(String titulo, String dni, String dataDeDevolucion) {
        logger.info("prestarLibro: " + titulo + " -> " + dni);

        LibrosCatalogado libroCatalogado = MapLibrosCatalogado.get(titulo);
        if (libroCatalogado == null) {
            logger.error("El libro '" + titulo + "' no existe en el catálogo.");
            return null;
        }

        if (libroCatalogado.getCantidad() <= 0) {
            logger.error("No hay ejemplares disponibles de '" + titulo + "'.");
            return null;
        }

        // Crear préstamo
        Prestar p = new Prestar(dni, libroCatalogado.getLibro(), LocalDate.now(), dataDeDevolucion);
        listprestars.add(p);

        // Asociar préstamo al lector
        MapPrestar.computeIfAbsent(dni, k -> new ArrayList<>()).add(p);

        // Actualizar cantidad
        libroCatalogado.setCantidad(libroCatalogado.getCantidad() - 1);

        logger.info("Libro prestado correctamente: " + titulo + " a " + dni);
        return p;
    }

    @Override
    public List<Prestar> consultarPrestaciones(String dni) {
        logger.info("consultarPrestaciones de " + dni);
        return MapPrestar.getOrDefault(dni, new ArrayList<>());
    }
}
