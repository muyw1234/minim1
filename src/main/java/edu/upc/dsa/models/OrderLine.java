package edu.upc.dsa.models;

public class OrderLine {
    private String productName;
    private int quantity;

    public OrderLine(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public OrderLine() {}

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
