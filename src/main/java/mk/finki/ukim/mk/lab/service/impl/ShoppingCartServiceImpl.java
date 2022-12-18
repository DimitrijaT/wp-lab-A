package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.OrderNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.OrderAlreadyInShoppingCartException;
import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository, OrderRepository orderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> listAllOrdersInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    @Transactional
    public ShoppingCart addOrderToShoppingCart(String username, Long orderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        if (shoppingCart.getOrders() == null) {
            shoppingCart.getOrders().add(order);
        } else {
            if (shoppingCart.getOrders().stream().filter(i -> i.getOrderId().equals(orderId)).toList().size() > 0)
                throw new OrderAlreadyInShoppingCartException(orderId, username);
            shoppingCart.getOrders().add(order);
        }
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<Order> listAllOrdersByUser(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUser(user).get();
        return this.shoppingCartRepository.findById(shoppingCart.getId()).get().getOrders();
    }

    @Override
    public List<Order> listAllOrders() {
        return shoppingCartRepository.findAll()
                .stream()
                .flatMap(x -> x.getOrders().stream())
                .collect(Collectors.toList());
    }
}
