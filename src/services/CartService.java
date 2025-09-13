package services;


import model.CartItem;
import model.Product;
import repository.CartRepository;

import java.util.ArrayList;
import java.util.Optional;

public class CartService {
    private ProductService productService;
    private CartRepository cartRepository;

    public CartService(ProductService productService){
        this.productService = productService;
        this.cartRepository = new CartRepository();
    }

    public void addProductToCart(String name, int quantity) {
        Optional<Product> product = productService.getProductByName(name);
        if(product.isPresent()) {
            if(quantity <= 0) {
                throw new IllegalArgumentException("The quantity must be greater than 0");
            }
            if (product.get().getStock() < quantity) {
                throw new IllegalArgumentException("Out of stock!");
            }

            Optional<CartItem> cartItem = cartRepository.findCartItemByName(name);
            if(cartItem.isPresent()) {

                int newQuantity = cartItem.get().getQuantity() + quantity;
                cartItem.get().setQuantity(newQuantity);
                cartRepository.updateTotalPriceByItem(cartItem.get());
                product.get().setStock(product.get().getStock() - newQuantity);
            }else{
                cartRepository.save(new CartItem(product.get(),quantity));
            }
        }else{
            throw new RuntimeException("The Product does not exist");
        }


    }


    public ArrayList<CartItem> getAllItemsFromCart(){
        return cartRepository.getCartItems();
    }


}
