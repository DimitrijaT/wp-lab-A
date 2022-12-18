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
                                Model model, HttpServletRequest req) {

        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        List<Order> orderList = null;

        if (username != null) {
            orderList = this.shoppingCartService.listAllOrdersByUser(username);
        }
        else if (dateFrom == null || dateTo == null) {
            orderList = this.shoppingCartService.listAllOrdersInShoppingCart(shoppingCart.getId());
        } else
            orderList = this.orderService.listAllOrdersBetween(dateFrom, dateTo);

        model.addAttribute("orderList", orderList);

        model.addAttribute("userList", this.authService.listAllUsers());

        model.addAttribute("bodyContent","userOrders");
        return "master-template";
    }

}
