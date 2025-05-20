package com.eventphotography.service;

import com.eventphotography.model.Booking;
import com.eventphotography.util.FileHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final FileHandler<Booking> fileHandler;
    private List<Booking> bookings;
    private Queue<Booking> bookingQueue;

    public BookingService() {
        this.fileHandler = new FileHandler<>("bookings.txt");
        this.bookings = new ArrayList<>();
        this.bookingQueue = new ArrayDeque<>();
        loadBookings();
    }

    private void loadBookings() {
        try {
            List<String> lines = fileHandler.readLines();
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length < 6) {
                    System.err.println("Invalid booking entry: " + line);
                    continue;
                }
                
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    int userId = Integer.parseInt(parts[1].trim());
                    int eventId = Integer.parseInt(parts[2].trim());
                    int photographerId = Integer.parseInt(parts[3].trim());
                    String date = parts[4].trim();
                    String status = parts[5].trim();
                    
                    Booking booking = new Booking(id, userId, eventId, photographerId, date, status);
                    bookings.add(booking);
                    if (status.equals("pending")) {
                        bookingQueue.add(booking);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in booking: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading bookings file: " + e.getMessage());
        }
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> getPendingBookings() {
        return bookings.stream()
                .filter(booking -> booking.getStatus().equals("pending"))
                .collect(Collectors.toList());
    }

    public void processNextBooking() throws IOException {
        Booking booking = bookingQueue.poll();
        if (booking != null) {
            booking.setStatus("confirmed");
            updateBooking(booking);
        }
    }

    public void deleteBooking(int id) throws IOException {
        bookings.removeIf(booking -> booking.getId() == id);
        bookingQueue.removeIf(booking -> booking.getId() == id);
        fileHandler.writeList(bookings);
    }

    public void addBooking(Booking booking) throws IOException {
        bookings.add(booking);
        if (booking.getStatus().equals("pending")) {
            bookingQueue.add(booking);
        }
        fileHandler.writeList(bookings);
    }

    public void updateBooking(Booking updatedBooking) throws IOException {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getId() == updatedBooking.getId()) {
                bookings.set(i, updatedBooking);
                break;
            }
        }
        fileHandler.writeList(bookings);
    }
}