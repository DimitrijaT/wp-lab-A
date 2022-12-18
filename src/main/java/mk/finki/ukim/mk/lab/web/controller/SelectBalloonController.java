package mk.finki.ukim.mk.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/selectBalloon")
public class SelectBalloonController {


    @GetMapping
    public String getSelectBalloonsPage(HttpServletRequest req, Model model) {
        model.addAttribute("bodyContent","selectBalloonSize");
        return "master-template";
    }

    @PostMapping
    public String saveSize(HttpServletRequest req, Model model){
        String sizeChosen = req.getParameter("size");
        req.getSession().setAttribute("sizeChosen", sizeChosen);
        return "redirect:/BalloonOrder";
    }

}
