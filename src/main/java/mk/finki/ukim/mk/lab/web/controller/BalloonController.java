package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;


    @ModelAttribute
    public void addManufacturers(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
    }

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloonList", balloons);
        model.addAttribute("bodyContent", "listBalloons");
        return "master-template";
    }

    @PostMapping
    public String selectBalloon(HttpServletRequest req) {
        String colorChosen = req.getParameter("color");
        req.getSession().setAttribute("colorChosen", colorChosen);
        return "redirect:/selectBalloon";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String saveBalloon(
            @RequestParam(required = false) Long balloonId,
            @RequestParam String balloonName,
            @RequestParam String balloonDescription,
            @RequestParam Long manufacturerId) {
        if (balloonId != null) {
            this.balloonService.edit(balloonId, balloonName, balloonDescription, manufacturerId);
        } else {
            this.balloonService.save(balloonName, balloonDescription, manufacturerId);
        }

        return "redirect:/balloons";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model) {
        model.addAttribute("bodyContent", "add-balloon");
        return "master-template";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            model.addAttribute("balloon", balloon);
            model.addAttribute("bodyContent", "add-balloon");
            return "master-template";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

}