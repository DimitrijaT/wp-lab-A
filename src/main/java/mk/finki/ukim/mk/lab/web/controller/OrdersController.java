package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final AuthService authService;

    public OrdersController(OrderService orderService, ShoppingCartService shoppingCartService, AuthService authService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @GetMapping
    public String getOrdersPage(@RequestParam(name = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                @RequestParam(name = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
                                @RequestParam(name = "username", required = false) String username,
                                Model model,
                                HttpServletRequest req) {


        String loggedUsername = req.getRemoteUser();
        List<Order> orderList;

        if (dateFrom != null && dateTo != null)
            orderList = listOrdersBetween(dateFrom, dateTo);
        else if (username != null)
            orderList = listChosenUserShoppingCart(username);
        else
            orderList = listChosenUserShoppingCart(loggedUsername);

        model.addAttribute("orderList", orderList);
        model.addAttribute("userList", this.authService.listAllUsers());
        model.addAttribute("bodyContent", "userOrders");
        return "master-template";
    }


    public List<Order> listOrdersBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return this.orderService.listAllOrdersBetween(dateFrom, dateTo);
    }

    public List<Order> listChosenUserShoppingCart(String loggedUsername) {
        if (loggedUsername == null || loggedUsername.isEmpty())
            return this.shoppingCartService.listAllOrders();
        return this.shoppingCartService.listAllOrdersByUser(loggedUsername);
    }


}
