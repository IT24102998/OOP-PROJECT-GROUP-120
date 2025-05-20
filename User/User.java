// User.java - Dummy content for scaffolding
package com.eventphotography.model;

public class User extends Person {
    private String role; // e.g., "client"

    public User(int id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getEmail() + "," + role;
    }
}