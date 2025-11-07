package edu.upc.dsa.services;


import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Product", description = "Endpoint to Product Service")
@Path("/product")
public class ProductService {

    private ProductManager pm;

    public ProductService() {
        this.pm = ProductManagerImpl.getInstance();

        if (this.pm.getProductsByPrice().isEmpty()) { // solo añadir si no existen
            this.pm.addProduct("P1", "Coca-Cola", 4.0);
            this.pm.addProduct("P2", "Fanta", 2.0);
            this.pm.addProduct("P3", "Nestea", 3.0);
        }
    }


    @GET
    @ApiOperation(value = "Get all products sorted by price ascending")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Product.class, responseContainer = "List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        List<Product> productsByPrice = this.pm.getProductsByPrice();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productsByPrice) {};
        return Response.ok(entity).build();
    }

    // ---- Añadir pedido ----
    @POST
    @ApiOperation(value = "Create a new order for a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Order.class, responseContainer = "List"),
    })
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order) {
        Order created = pm.addOrder(order.getUsername(), order.getDni(), order.getProducts());
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // ---- Servir pedido ----
    @POST
    @Path("/deliver")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Serve the next order in queue")
    public Response deliverOrder() {
        Order o = pm.deliverOrder();
        if (o == null)
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.ok(o).build();
    }

    // ---- Obtener pedidos de un usuario ----
    @GET
    @Path("/user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get all orders of a user by username")
    public Response getUserOrders(@PathParam("username") String username) {
        User u = pm.getUser(username);
        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado: " + username)
                    .build();
        }

        GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(u.getOrderList()) {};
        return Response.ok(entity).build();
    }
}

