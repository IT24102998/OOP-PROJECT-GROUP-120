package com.eventphotography.service;

import com.eventphotography.model.Booking;
import com.eventphotography.model.Event;
import com.eventphotography.model.Payment;
import com.eventphotography.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final FileHandler<Payment> fileHandler;
    private List<Payment> payments;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private EventService eventService;

    public PaymentService() {
        this.fileHandler = new FileHandler<>("data/payments.txt");
        this.payments = new ArrayList<>();
        loadPayments();
    }

    private void loadPayments() {
        try {
            List<String> lines = fileHandler.readLines();
            for (String line : lines) {
                if (line.trim().isEmpty() || line.trim().equals("[]")) {
                    continue;
                }
                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        int id = Integer.parseInt(parts[0].trim());
                        int bookingId = Integer.parseInt(parts[1].trim());
                        double amount = Double.parseDouble(parts[2].trim());
                        String paymentDate = parts[3].trim();
                        String status = parts[4].trim();
                        payments.add(new Payment(id, bookingId, amount, paymentDate, status));
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping invalid payment line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading payments: " + e.getMessage());
        }
    }

    public void addPayment(Payment payment) throws IOException {
        payments.add(payment);
        fileHandler.writeList(payments);
    }

    public List<Payment> getAllPayments() {
        return payments;
    }
    
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    
    public Map<Integer, Event> getAllEvents() {
        return eventService.getAllEvents().stream()
            .collect(Collectors.toMap(Event::getId, event -> event));
    }

    public Payment getPaymentById(int id) {
        return payments.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void updatePayment(Payment updatedPayment) throws IOException {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId() == updatedPayment.getId()) {
                payments.set(i, updatedPayment);
                break;
            }
        }
        fileHandler.writeList(payments);
    }

    public void deletePayment(int id) throws IOException {
        payments.removeIf(p -> p.getId() == id);
        fileHandler.writeList(payments);
    }
}