package repository;


import model.Product;

import java.util.ArrayList;
import java.util.Optional;


public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository(){
        products = new ArrayList<>();
    }

    public void save(Product newProduct){
        products.add(newProduct);
    }

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public Optional<Product> getProductByName(String name){
        return  products.stream()
                .filter(entry -> entry.getName().equalsIgnoreCase(name)).findFirst();
    }

    public void updateStock(String productName,int newStock){
        Optional<Product> product = getProductByName(productName);
        product.ifPresent(value -> value.setStock(newStock));
    }

}
