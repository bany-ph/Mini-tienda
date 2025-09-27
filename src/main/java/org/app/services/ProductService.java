package org.app.services;

import model.FoodStuff;
import model.HouseHold;
import model.Product;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public void addProduct(String name, String strPrice, String strStock, String choice)  {
        double price = Double.parseDouble(strPrice);
        int stock = Integer.parseInt(strStock);
        // Change the product name to lowerCase
        String productNameLower = name.toLowerCase();

        if( price < 0 || stock < 0) {
            throw new RuntimeException("The price and stock cannot be 0 or less");
        }

        if(name.isEmpty()) {
            throw new RuntimeException("The inputs cannot be empty");
        }

        if(productRepository.getProductByName(productNameLower).isPresent()) {
            throw new IllegalArgumentException("The product already exists!");
        }

        if (choice == null) throw new RuntimeException("Select what type of product you want to add");

        Product newProduct;
        if(choice.equalsIgnoreCase("Electrodomestico")){
            newProduct = new HouseHold(name,price,stock);
        } else {
            newProduct = new FoodStuff(name,price,stock);
        }
        productRepository.save(newProduct);

    }
    public Product getMostExpensiveProduct(){
        return productRepository.getAllProducts().stream().max(Comparator.comparing(Product::getPrice)).orElseThrow();
    }
    public Product getMostCheaperProduct(){
        return productRepository.getAllProducts().stream().min(Comparator.comparing(Product::getPrice)).orElseThrow();
    }

    public ArrayList<Product> getAllProducts(){
        // return all products
        return productRepository.getAllProducts();
    }

    public ArrayList<Product> getAllPartiallyProducts(String productName){
        return productRepository.getAllProducts().stream()
                .filter(product -> product.getName().contains(productName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<Product> findProductByName(String productName){
        return productRepository.getProductByName(productName);
    }

    public Optional<Product> getProductByName(String name){
        return productRepository.getProductByName(name);
    }

    public void updateStock(String name, int stock){
        int newStock = findProductByName(name).get().getStock() - stock;

        if(newStock < 0) throw new RuntimeException("Out of Stock!");

        productRepository.updateStock(name,newStock);
    }


}
