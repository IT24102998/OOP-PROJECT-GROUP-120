package com.eventphotography.controller;

import com.eventphotography.model.Booking;
import com.eventphotography.model.Event;
import com.eventphotography.model.Payment;
import com.eventphotography.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        
        // Get bookings and events for the dropdown
        model.addAttribute("bookings", paymentService.getAllBookings());
        model.addAttribute("events", paymentService.getAllEvents());
        
        return "paymentList";
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute Payment payment) throws IOException {
        paymentService.addPayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String editPayment(@PathVariable int id, Model model) throws IOException {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        model.addAttribute("bookings", paymentService.getAllBookings());
        return "paymentEdit";
    }

    @PostMapping("/update")
    public String updatePayment(@ModelAttribute Payment payment) throws IOException {
        paymentService.updatePayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable int id) throws IOException {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }
}