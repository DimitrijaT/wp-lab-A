package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    public ShoppingCart getActiveShoppingCart(String username);

    public List<Order> listAllOrdersInShoppingCart(Long cartId);

    public ShoppingCart addOrderToShoppingCart(String username, Long productId);
}
