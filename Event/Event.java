package com.eventphotography.model;

public class Event {
    private int id;
    private String description;
    private String type;
    private String date;
    private String location;

    public Event() {}

    public Event(int id, String description, String type, String date, String location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.date = date;
        this.location = location;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return id + "," + description + "," + type + "," + date + "," + location;
    }
}