package org.app.model;


public class CartItem {
    /* Product (name,price,stock) */
    private Product product;
    private int quantity;
    private double totalPrice;

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalPrice() {return totalPrice;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}


}
