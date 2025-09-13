package repository;


import model.CartItem;

import java.util.ArrayList;
import java.util.Optional;

public class CartRepository {
    private ArrayList<CartItem> cartItems;

    public CartRepository() {
        cartItems = new ArrayList<>();
    }

    public void save(CartItem cartItem) {
        cartItems.add(cartItem);
        updateTotalPriceByItem(cartItem);
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void updateTotalPriceByItem(CartItem cartItem){
            cartItem.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
    }

    public double getTotalPrice() {
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }


    public Optional<CartItem> findCartItemByName(String name) {
        return  cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getName().equalsIgnoreCase(name))
                .findFirst();
    }

}
