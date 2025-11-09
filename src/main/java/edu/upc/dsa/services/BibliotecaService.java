package edu.upc.dsa.services;

import edu.upc.dsa.BibliotecaManager;
import edu.upc.dsa.BibliotecaManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/Biblioteca", description = "Endpoint del servicio Biblioteca")
@Path("/Biblioteca")
public class BibliotecaService {

    private final BibliotecaManager bm;

    public BibliotecaService() {
        this.bm = BibliotecaManagerImpl.getInstance();

        // Datos de ejemplo iniciales
        this.bm.addLector("Ana", "A", "1", "2000-1-1", "BCN", "1@gmail.com");
        this.bm.addLector("Marc", "B", "2", "2000-2-2", "BCN", "2@gmail.com");
        this.bm.addLibro("1", "libro1", "Planeta", "2001", "1", "Pablo", "Novela");
        this.bm.addLibro("2", "libro2", "SM", "2002", "2", "Ema", "Infantil");
    }


    // Añadir lector
    @POST
    @Path("/addLector")
    @ApiOperation(value = "Añadir un nuevo lector")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lector añadido correctamente", response = Lector.class, responseContainer = "Lector")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLector(@QueryParam("nombre") String nombre,
                              @QueryParam("apellido") String apellido,
                              @QueryParam("dni") String dni,
                              @QueryParam("fechaNacimiento") String fechaNacimiento,
                              @QueryParam("lugarNacimiento") String lugarNacimiento,
                              @QueryParam("email") String email) {

        if (nombre == null || apellido == null || dni == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("Faltan parámetros obligatorios").build();

        Lector lector = bm.addLector(nombre, apellido, dni, fechaNacimiento, lugarNacimiento, email);
        return Response.status(Response.Status.CREATED).entity(lector).build();
    }


    //Añadir libro
    @POST
    @Path("/addLibro")
    @ApiOperation(value = "Añadir un nuevo libro a la cola de catalogación")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Libro añadido correctamente", response = Libro.class, responseContainer = "Libro"),
            @ApiResponse(code = 400, message = "Datos incorrectos o incompletos")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLibro(@QueryParam("ISBN") String ISBN,
                             @QueryParam("titulo") String titulo,
                             @QueryParam("editorial") String editorial,
                             @QueryParam("anioPublicacion") String anioPublicacion,
                             @QueryParam("numeroEdicion") String numeroEdicion,
                             @QueryParam("autor") String autor,
                             @QueryParam("tematica") String tematica) {

        if (ISBN == null || titulo == null || editorial == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Parámetros ISBN, titulo y editorial son obligatorios").build();

        Libro libro = bm.addLibro(ISBN, titulo, editorial, anioPublicacion, numeroEdicion, autor, tematica);
        GenericEntity<Libro> entity = new GenericEntity<Libro>(libro) {};
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    //Catalogar
    @POST
    @Path("/catalogar")
    @ApiOperation(value = "Catalogar libros del montón más grande")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Libros catalogados correctamente", response = LibrosCatalogado.class, responseContainer = "List")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogar() {
        return Response.ok(bm.catalogar()).build();
    }

    //Prestar libro
    @POST
    @Path("/prestar")
    @ApiOperation(value = "Prestar un libro a un lector")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Libro prestado correctamente", response = Prestar.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Libro no encontrado o sin stock")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response prestarLibro(@QueryParam("titulo") String titulo,
                                 @QueryParam("dni") String dni,
                                 @QueryParam("fechaDevolucion") String fechaDevolucion) {

        if (titulo == null || dni == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Título y DNI son obligatorios").build();

        Prestar p = bm.prestarLibro(titulo, dni, fechaDevolucion);
        if (p == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se pudo realizar el préstamo").build();

        return Response.ok(p).build();
    }

    //Consultar préstamos de un lector
    @GET
    @Path("/prestamos")
    @ApiOperation(value = "Consultar préstamos de un lector por DNI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listado de préstamos", response = Prestar.class, responseContainer = "List")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPrestamos(@QueryParam("dni") String dni) {

        if (dni == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("El DNI es obligatorio").build();
        List<Prestar> prestamos = bm.consultarPrestaciones(dni);
        GenericEntity<List<Prestar>> entity = new GenericEntity<List<Prestar>>(prestamos) {};
        return Response.ok(entity).build();
    }

}



