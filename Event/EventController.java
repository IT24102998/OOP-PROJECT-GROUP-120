package com.eventphotography.controller;

import com.eventphotography.model.Event;
import com.eventphotography.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String listEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "eventList"; // JSP to show all events
    }

    @PostMapping("/add")
    public String addEvent(@ModelAttribute Event event) throws IOException {
        eventService.addEvent(event);
        return "redirect:/events";
    }

    @PostMapping("/update")
    public String updateEvent(@ModelAttribute Event event) throws IOException {
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) throws IOException {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String editEventForm(@PathVariable int id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "editEvent"; // JSP page to edit event
    }
}