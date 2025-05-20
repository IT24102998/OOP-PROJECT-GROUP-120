// Review.java - Dummy content for scaffolding
package com.eventphotography.model;

public class Review {
    private int id;
    private int photographerId;
    private int userId;
    private double rating;
    private String comment;

    public Review(int id, int photographerId, int userId, double rating, String comment) {
        this.id = id;
        this.photographerId = photographerId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPhotographerId() { return photographerId; }
    public void setPhotographerId(int photographerId) { this.photographerId = photographerId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return id + "," + photographerId + "," + userId + "," + rating + "," + comment;
    }
}