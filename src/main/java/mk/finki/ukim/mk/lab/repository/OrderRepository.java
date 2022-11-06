package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orders;


    @PostConstruct
    public void init() {
        orders = new ArrayList<>();
    }


    public List<Order> listAll() {
        return this.orders;
    }

    public Order placeOrder(Order order) {
        this.orders.removeIf(x -> x.getOrderId().equals(order.getOrderId()));
        this.orders.add(order);
        return order;
    }

}
