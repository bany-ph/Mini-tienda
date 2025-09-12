package services;

import model.Product;
import repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public void addProduct(Product product)  {

        if(product.getPrice() < 0 || product.getStock() < 0) throw new RuntimeException("The price and stock cannot be 0 or less");

        if(productRepository.getProductNames().contains(product.getName())) throw new RuntimeException("The product already exists!");

        if(product.getName().isEmpty()) throw new RuntimeException("The inputs cannot be empty");



        // if each input is correct then → add product
        productRepository.save(product.getName().toLowerCase(),product.getPrice(),product.getStock());
    }



    public String getAllProducts(){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < productRepository.getProducts().size() ; i++) {
            res.append(String.format("%-20s %s,  %s \n", productRepository.getProductNames().get(i).toUpperCase() ,
                    productRepository.getPrices()[i], productRepository.getProducts().get(productRepository.getProductNames().get(i))));

        }
        return res.toString();
    }

    public String findProductByName(String productName){
        productName = productName.toLowerCase();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < productRepository.getProducts().size() ; i++) {
            if(productRepository.getProductNames().get(i).toLowerCase().contains(productName)){
                res.append(String.format("%-20s %s,  %s \n", productRepository.getProductNames().get(i).toUpperCase() ,
                       productRepository.getPrices()[i], productRepository.getProducts().get(productName)));
            }
        }
        return res.toString();
    }




}
