package mk.finki.ukim.mk.lab.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ConfirmationInfo")
public class ConfirmationInfoController {

    @GetMapping
    public String getConfirmationInfo(HttpServletRequest req, Model model) {

        model.addAttribute("clientBrowser", req.getHeader("User-Agent"));
        model.addAttribute("clientIpAddress", req.getRemoteAddr());

        model.addAttribute("bodyContent", "confirmationInfo");
        return "master-template";
    }
}
