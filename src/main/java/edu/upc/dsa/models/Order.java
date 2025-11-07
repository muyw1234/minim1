package edu.upc.dsa.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private String username;
    private String dni;
    private List<OrderLine> products;
    private boolean served;

    public Order(String username, String dni, List<OrderLine> products) {
        this.username = username;
        this.dni = dni;
        this.products = (products != null) ? products : new ArrayList<>();
        this.served = false;
    }

    public Order() {
        this.products = new ArrayList<>();
        this.served = false;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public List<OrderLine> getProducts() { return products; }
    public void setProducts(List<OrderLine> products) { this.products = products; }

    public boolean isServed() { return served; }
    public void setServed(boolean served) { this.served = served; }
}