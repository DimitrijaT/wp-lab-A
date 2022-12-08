package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
        return "listBalloons";
    }

    @PostMapping
    public String selectBalloon(HttpServletRequest req) {
        String colorChosen = req.getParameter("color");
        req.getSession().setAttribute("colorChosen", colorChosen);
        return "redirect:/selectBalloon";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String balloonName,
                              @RequestParam String balloonDescription,
                              @RequestParam Long manufacturerId) {
        this.balloonService.save(balloonName, balloonDescription, manufacturerId);
        return "redirect:/balloons";
    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model) {
        return "add-balloon";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            model.addAttribute("balloon", balloon);
            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

}