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
        if(quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be greater than 0");
        }

        Optional<Product> product = productService.getProductByName(name);
        Optional<CartItem> cartItem = cartRepository.findCartItemByName(name);
        if(product.isPresent()) {
            if ( quantity > product.get().getStock() ) {
                throw new IllegalArgumentException("Out of stock!");
            }

            if(cartItem.isPresent()) {
                int newQuantityCart = cartItem.get().getQuantity() + quantity;
                cartItem.get().setQuantity(newQuantityCart);
                cartRepository.updateTotalPriceByItem(cartItem.get());
            }else{
                cartRepository.save(new CartItem(product.get(),quantity));
            }
            productService.updateStock(name,quantity);
        }else{
            throw new RuntimeException("The Product does not exist");
        }


    }

    public double getTotalCart(){
        return cartRepository.getTotalPrice();
    }

    public ArrayList<CartItem> getAllItemsFromCart(){
        return cartRepository.getCartItems();
    }


}
