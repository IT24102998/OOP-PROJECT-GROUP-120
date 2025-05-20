// Booking.java - Dummy content for scaffolding
package com.eventphotography.model;

public class Booking {
    private int id;
    private int userId;
    private int eventId;
    private int photographerId;
    private String date;
    private String status;

    public Booking(int id, int userId, int eventId, int photographerId, String date, String status) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.photographerId = photographerId;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public int getPhotographerId() { return photographerId; }
    public void setPhotographerId(int photographerId) { this.photographerId = photographerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return id + "," + userId + "," + eventId + "," + photographerId + "," + date + "," + status;
    }
}