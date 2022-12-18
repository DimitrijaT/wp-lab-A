package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart getActiveShoppingCart(String username);

    List<Order> listAllOrdersInShoppingCart(Long cartId);

    ShoppingCart addOrderToShoppingCart(String username, Long productId);

    List<Order> listAllOrdersByUser(String username);

    List<Order> listAllOrders();
}
