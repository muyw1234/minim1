package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaManagerTest {
    BibliotecaManagerImpl manager;

    @Before
    public void setUp() {
        manager = BibliotecaManagerImpl.getInstance();
    }

    @Test
    public void testAddLector() {
        Lector lector = manager.addLector("Anna", "Serra", "123A", "1990-02-02", "Barcelona", "anna@mail.com");
        assertEquals("123A", lector.getDni());
        assertEquals("Anna", lector.getNombrelector());
    }

    @Test
    public void testAddLibroYMonton() {
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        manager.addLibro("ISBN2", "Animal Farm", "Secker & Warburg", "1945", "1", "George Orwell", "Sátira");

        LibrosCatalogado catalogado = manager.catalogar();
        assertNotNull(catalogado);
        assertEquals("ISBN2", catalogado.getLibro().getISBN());
    }

    @Test
    public void testCatalogar() {
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        LibrosCatalogado c = manager.catalogar();
        assertNotNull(c);
        assertEquals("1984", c.getLibro().getTitulo());
        assertEquals(1, c.getCantidad());
    }

    @Test
    public void testPrestarLibro() {
        manager.addLector("Anna", "Serra", "123A", "1990-02-02", "Barcelona", "anna@mail.com");
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        manager.catalogar();

        Prestar p = manager.prestarLibro("1984", "123A", "2025-12-31");
        assertNotNull(p);
        assertEquals("1984", p.getLibroTitulo());
        assertEquals("123A", p.getLectordni());
        assertEquals("En tramite", p.getEstado());
    }

    @Test
    public void testConsultarPrestamos() {
        manager.addLector("Anna", "Serra", "123A", "1990-02-02", "Barcelona", "anna@mail.com");
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        manager.catalogar();

        manager.prestarLibro("1984", "123A", "2025-12-31");

        List<Prestar> prestamos = manager.consultarPrestaciones("123A");
        assertEquals(1, prestamos.size());
        assertEquals("123A", prestamos.get(0).getLectordni());
    }
}
