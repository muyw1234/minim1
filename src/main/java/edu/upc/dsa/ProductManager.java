package edu.upc.dsa;

//import com.sun.org.apache.xpath.internal.operations.Or;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.OrderLine;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;
import java.util.Map;

public interface ProductManager {

    public Product addProduct(String id, String name, double price);
    public List<Product> getProductsByPrice();
    Product getProduct(String c1);

    Order addOrder(String username, String dni, List<OrderLine> products);
    int numOrders();
    Order deliverOrder();


    // Usuarios
    User getUser(String dni);
}
