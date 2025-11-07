package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.OrderLine;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);
    private static ProductManagerImpl instance;

    private List<Product> productList;
    private Queue<Order> orderQueue;
    private Map<String, User> users; // clave = username

    private ProductManagerImpl() {
        this.productList = new ArrayList<>();
        this.orderQueue = new LinkedList<>();
        this.users = new HashMap<>();
    }

    public static ProductManagerImpl getInstance() {
        if (instance == null) {
            instance = new ProductManagerImpl();
            logger.info("ProductManagerImpl Singleton instance created");
        }
        return instance;
    }

    // ---------- PRODUCTOS ----------
    @Override
    public Product addProduct(String id, String name, double price) {
        Product p = new Product(id, name, price);
        productList.add(p);
        logger.info("Producto añadido: " + p);
        return p;
    }

    @Override
    public List<Product> getProductsByPrice() {
        logger.info("getProductsByPrice()");
        List<Product> sorted = new ArrayList<>(productList);
        sorted.sort(Comparator.comparing(Product::getPrice));
        return sorted;
    }

    public Product getProduct(String name) {
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    // ---------- PEDIDOS ----------
    @Override
    public Order addOrder(String username, String dni, List<OrderLine> products) {
        logger.info("Añadiendo pedido del usuario " + username + " (DNI: " + dni + ")");

        // Buscar o crear usuario
        User user = users.get(dni);
        if (user == null) {
            user = new User(username, dni);
            users.put(dni, user);   // ✅ guardar por dni
        }


        // Crear el pedido y asociarlo
        Order order = new Order(username, dni, products);
        orderQueue.add(order);
        user.addOrder(order);

        logger.info("Pedido añadido correctamente para " + username);
        return order;
    }

    @Override
    public Order deliverOrder() {
        logger.info("Servir pedido (FIFO)");
        Order order = orderQueue.poll();
        if (order == null) {
            logger.warn("No hay pedidos en cola");
            return null;
        }

        // Actualizar ventas de productos
        for (OrderLine line : order.getProducts()) {
            Product p = getProduct(line.getProductName());
            if (p != null) {
                // p.addSales(line.getQuantity()); // si lo implementas
            } else {
                logger.error("Producto no encontrado -> " + line.getProductName());
            }
        }

        order.setServed(true);
        logger.info("Pedido servido al usuario: " + order.getUsername());
        return order;
    }

    @Override
    public int numOrders() {
        return orderQueue.size();
    }

    @Override
    public User getUser(String dni) {
        for (User u : users.values()) {
            if (u.getDni().equalsIgnoreCase(dni)) {
                return u;
            }
        }
        return null;
    }

}