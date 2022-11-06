package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String balloonSize, String clientName, String address) {
        if (balloonColor == null || balloonColor.isEmpty() || clientName == null || clientName.isEmpty() || address == null || address.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        Order order = new Order(balloonColor, balloonSize, clientName, address);
        return this.orderRepository.placeOrder(order);
    }

    @Override
    public List<Order> listAll() {
        return this.orderRepository.listAll();
    }


}
