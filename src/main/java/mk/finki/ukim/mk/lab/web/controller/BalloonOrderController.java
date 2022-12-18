package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/BalloonOrder")
public class BalloonOrderController {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    public BalloonOrderController(ShoppingCartService shoppingCartService, OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getBalloonOrderPage(Model model) {
        model.addAttribute("bodyContent","deliveryInfo");
        return "master-template";
    }

    @PostMapping
    public String saveOrder(@RequestParam String clientName,
                            @RequestParam(required = false) String clientAddress,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreated,
                            HttpServletRequest req) {

        String sizeChosen = req.getSession().getAttribute("sizeChosen").toString();
        String colorChosen = req.getSession().getAttribute("colorChosen").toString();
        req.getSession().setAttribute("clientName",clientName);
        req.getSession().setAttribute("clientAddress",clientAddress);


        Order order = this.orderService.placeOrder(sizeChosen, colorChosen, dateCreated);
        this.shoppingCartService.addOrderToShoppingCart(clientName, order.getOrderId());

        return "redirect:/ConfirmationInfo";

    }
}
