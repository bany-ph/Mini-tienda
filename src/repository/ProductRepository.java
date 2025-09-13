package repository;


import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/*
*   repository simulation with a fake database ↓
*   the database is three collection.
* */
public class ProductRepository {
    private FakeDataBase db;
    private ArrayList<Product> products;

    public ProductRepository(){

        db = new FakeDataBase();
        products = new ArrayList<>();
    }

    public void save(String name, double price, int stock){
        // for the user history
            db.setPrices(price);
            db.setProductsName(name);
            db.setProducts(name,stock);

         // for me
            products.add(new Product(name,price,stock));

    }

    public void updateStock(String name, int stock){
        db.getProducts().put(name,db.getProducts().get(name) + stock);
    }
    public HashMap<String,Integer> getProducts(){
        return db.getProducts();
    }

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public Optional<Product> getProductByName(String name){
        return  products.stream()
                .filter(entry -> entry.getName().equalsIgnoreCase(name)).findFirst();
    }

    public ArrayList<String> getProductNames(){
        return db.getProductsName();
    }
    public double[] getPrices(){
        return db.getPrices();
    }
}
