package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order placeOrder(String balloonColor, String balloonSize, LocalDateTime dateCreated);

    List<Order> listUserOrders(String username);

    public List<Order> listAllOrdersBetween(LocalDateTime from, LocalDateTime to);

}
