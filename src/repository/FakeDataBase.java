package repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FakeDataBase {
    private HashMap<String,Integer> products;
    private ArrayList<String> productsName;
    private double[] prices;

    public FakeDataBase(){
        products = new HashMap<>();
        productsName = new ArrayList<>();
        prices = new double[0];
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(String productName, Integer stock) {
       products.put(productName,stock);
    }

    public ArrayList<String> getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName.add(productsName);
    }

    public double[] getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        // array
        this.prices = Arrays.copyOf(this.prices,this.prices.length + 1);
        this.prices[this.prices.length - 1] = prices;

    }
}
