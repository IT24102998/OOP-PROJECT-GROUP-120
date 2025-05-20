package com.eventphotography.controller;

import com.eventphotography.model.Booking;
import com.eventphotography.model.Event;
import com.eventphotography.model.Photographer;
import com.eventphotography.service.BookingService;
import com.eventphotography.service.EventService;
import com.eventphotography.service.PhotographerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final EventService eventService;
    private final PhotographerService photographerService;

    public BookingController(BookingService bookingService, EventService eventService, PhotographerService photographerService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
        this.photographerService = photographerService;
    }

    @GetMapping
    public String listBookings(Model model) {
        List<Booking> allBookings = bookingService.getAllBookings();
        List<Event> events = eventService.getAllEvents();
        List<Photographer> photographers = photographerService.getAllPhotographers();
        
        model.addAttribute("bookings", allBookings);
        model.addAttribute("events", events);
        model.addAttribute("photographers", photographers);
        return "bookingList";
    }

    @PostMapping("/add")
    public String addBooking(
        @RequestParam("eventId") int eventId,
        @RequestParam("photographerId") int photographerId,
        @RequestParam("date") String date,
        @RequestParam("status") String status
    ) throws IOException {
        Booking booking = new Booking(
            bookingService.getAllBookings().size() + 1, // Generate new ID
            1, // TODO: Get actual user ID from session
            eventId,
            photographerId,
            date,
            status.isEmpty() ? "pending" : status
        );
        bookingService.addBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/process")
    public String processNextBooking() throws IOException {
        bookingService.processNextBooking();
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable int id) throws IOException {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
