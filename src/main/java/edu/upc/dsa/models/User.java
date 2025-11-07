package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String dni;
    private List<Order> orderList;

    public User(String username, String dni) {
        this.username = username;
        this.dni = dni;
        this.orderList = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public List<Order> getOrderList() { return orderList; }

    public void addOrder(Order order) {
        orderList.add(order);
    }
}
