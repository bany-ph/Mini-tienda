package services;

import model.Product;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public void addProduct(Product product)  {

        if(product.getPrice() < 0 || product.getStock() < 0) {
            throw new RuntimeException("The price and stock cannot be 0 or less");
        }

        if(product.getName().isEmpty()) {
            throw new RuntimeException("The inputs cannot be empty");
        }

        // Change the product name to lowerCase
        String productNameLower = product.getName().toLowerCase();
        if(productRepository.getProductNames().contains(productNameLower)) {
            throw new IllegalArgumentException("The product already exists!");
        }


        // Save
        productRepository.save(productNameLower, product.getPrice(), product.getStock());
    }



    public ArrayList<Product> getAllProducts(){
        // return all products
        return (ArrayList<Product>) productRepository.getProducts().entrySet().stream()
                .map(entry -> new Product(entry.getKey(), productRepository.getPrices()[productRepository.getProductNames().indexOf(entry.getKey())],
                        entry.getValue()))
                .collect(Collectors.toList());
    }

    public ArrayList<Product> getAllPartiallyProducts(String productName){
        /*
         *  .filter → if each product from productRepository.getProducts() contains productName, then ↓
         *  .map → creates a new Product object with the data saved in fakeDatabase and returns it ↓
         *  .toList → returns a list of Product objects returned by the map function
         * */
        return (ArrayList<Product>) productRepository.getProducts().entrySet().stream()
                .filter(entry -> entry.getKey().toLowerCase().contains(productName.toLowerCase()))
                .map(entry -> new Product(entry.getKey(), productRepository.getPrices()[productRepository.getProductNames().indexOf(entry.getKey())],
                        entry.getValue()))
                .collect(Collectors.toList());
            
    }

    public Optional<Product> findProductByName(String productName){
        productName = productName.toLowerCase().trim();
        return productRepository.getProducts().containsKey(productName) ?
                Optional.of(new Product(productName, productRepository.getPrices()[productRepository.getProductNames().indexOf(productName)],
                        productRepository.getProducts().get(productName)))
                : Optional.empty();
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
