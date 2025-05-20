// Photographer.java - Dummy content for scaffolding
package com.eventphotography.model;

public class Photographer extends Person {
    private double rating;

    public Photographer(int id, String name, String email, double rating) {
        super(id, name, email);
        this.rating = rating;
    }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getEmail() + "," + rating;
    }
}