package model;

public class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public Product(){}

    public void setPrice(double price){
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
