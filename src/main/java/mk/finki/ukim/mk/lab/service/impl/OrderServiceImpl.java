package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
    }


    @Transactional
    public Order placeOrder(String balloonColor, String balloonSize, LocalDateTime dateCreated) {
        if (balloonColor == null || balloonColor.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return this.orderRepository.save(new Order(balloonColor, balloonSize, dateCreated));
    }

    @Override
    public List<Order> listUserOrders(String username) {
        return this.shoppingCartService.getActiveShoppingCart(username).getOrders();
    }

    @Override
    public List<Order> listAllOrdersBetween(LocalDateTime from, LocalDateTime to) {
        return this.orderRepository.findByDateCreatedBetween(from, to);
    }

}
