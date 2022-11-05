package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.impl.OrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-order", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Gi isprakjame ovie 2 parametri:
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");

        String colorChosen = req.getSession().getAttribute("colorChosen").toString();

        OrderService orderService = new OrderServiceImpl();

        try {
            Order order = orderService.placeOrder(colorChosen, clientName, clientAddress);
        } catch (InvalidArgumentsException ex) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("errorText", ex.getMessage());
            springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
            return;
        }

        ServletContext servletContext = getServletContext();
        int count = (int) servletContext.getAttribute("orderCount");
        count += 1;
        servletContext.setAttribute("orderCount",count);

        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);
        req.getSession().setAttribute("clientBrowser", req.getHeader("User-Agent"));
        req.getSession().setAttribute("clientIpAddress", req.getRemoteAddr());

        resp.sendRedirect("/ConfirmationInfo");
    }
}
