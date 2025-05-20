// PhotographerController.java - Dummy content for scaffolding
package com.eventphotography.controller;

import com.eventphotography.model.Photographer;
import com.eventphotography.service.PhotographerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/photographers")
public class PhotographerController {
    private final PhotographerService photographerService;

    public PhotographerController() {
        this.photographerService = new PhotographerService();
    }

    @GetMapping
    public String listPhotographers(Model model) {
        model.addAttribute("photographers", photographerService.getAllPhotographers());
        return "photographerList";
    }

    @PostMapping("/add")
    public String addPhotographer(@ModelAttribute Photographer photographer) throws IOException {
        photographerService.addPhotographer(photographer);
        return "redirect:/photographers";
    }

    @PostMapping("/update")
    public String updatePhotographer(@ModelAttribute Photographer photographer) throws IOException {
        photographerService.updatePhotographer(photographer);
        return "redirect:/photographers";
    }

    @GetMapping("/delete/{id}")
    public String deletePhotographer(@PathVariable int id) throws IOException {
        photographerService.deletePhotographer(id);
        return "redirect:/photographers";
    }

    @GetMapping("/sort")
    public String sortPhotographers() throws IOException {
        photographerService.bubbleSortByRating();
        return "redirect:/photographers";
    }
}