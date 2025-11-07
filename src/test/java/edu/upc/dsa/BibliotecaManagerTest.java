package edu.upc.dsa;

import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.LibrosCatalogado;
import edu.upc.dsa.models.MontonDeLibros;
import edu.upc.dsa.models.Prestar;
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
    public void testAddLectorYGetLector() {
        Lector l = manager.addLector("Anna", "Serra", "123A", "1990-02-02", "Barcelona", "anna@mail.com");
        assertEquals(l.getDni(), "123A");
    }

    @Test
    public void testAddLibroYMonton() {
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        manager.addLibro("ISBN2", "Animal Farm", "Secker & Warburg", "1945", "1", "George Orwell", "Sátira");

        MontonDeLibros m = manager.añadirAlMonton(null);
        assertNotNull(m);
        assertTrue(m.getMontonlibros().size() > 0);
    }

    @Test
    public void testCatalogarYPrestar() {
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        MontonDeLibros m = manager.añadirAlMonton(null);

        List<LibrosCatalogado> catalogados = manager.catalogar(m);
        assertFalse(catalogados.isEmpty());

        Prestar p = manager.prestarLibro("1984", "123A", "2025-12-31");
        assertNotNull(p);
        assertEquals("123A", p.getLectordni());
        assertEquals("1984", p.getLibroId().getTitulo());
    }

    @Test
    public void testConsultarPrestamos() {
        manager.addLibro("ISBN1", "1984", "Secker & Warburg", "1949", "1", "George Orwell", "Distopía");
        MontonDeLibros m = manager.añadirAlMonton(null);
        manager.catalogar(m);
        manager.prestarLibro("1984", "123A", "2025-12-31");

        List<Prestar> prestamos = manager.consultarPrestaciones("123A");
        assertFalse(prestamos.isEmpty());
        assertEquals("123A", prestamos.get(0).getLectordni());
    }
}
