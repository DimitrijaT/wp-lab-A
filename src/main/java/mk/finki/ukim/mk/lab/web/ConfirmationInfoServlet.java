package mk.finki.ukim.mk.lab.web;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmation-info-servlet", urlPatterns = "/ConfirmationInfoServlet")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;

//        model.addAttribute("clientName", clientName);
//        model.addAttribute("clientAddress", clientAddress);
//        model.addAttribute("clientBrowser", req.getHeader("User-Agent"));
//        model.addAttribute("clientName", clientName);
//        model.addAttribute("clientIpAddress", req.getRemoteAddr());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }

}
