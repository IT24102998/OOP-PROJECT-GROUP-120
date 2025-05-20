package com.eventphotography.service;

import com.eventphotography.model.Event;
import com.eventphotography.util.FileHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final FileHandler<Event> fileHandler;
    private final List<Event> events;

    public EventService() {
        this.fileHandler = new FileHandler<>("events.txt");
        this.events = new ArrayList<>();
        loadEvents(); // Load from file if needed
    }

    private void loadEvents() {
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
                        String description = parts[1].trim();
                        String type = parts[2].trim();
                        String date = parts[3].trim();
                        String location = parts[4].trim();
                        events.add(new Event(id, description, type, date, location));
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Skip invalid lines
                    System.err.println("Skipping invalid event line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading events: " + e.getMessage());
        }
    }

    public void addEvent(Event event) throws IOException {
        events.add(event);
        fileHandler.writeList(events);
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public Event getEventById(int id) {
        return events.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void updateEvent(Event updatedEvent) throws IOException {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == updatedEvent.getId()) {
                events.set(i, updatedEvent);
                break;
            }
        }
        fileHandler.writeList(events);
    }

    public void deleteEvent(int id) throws IOException {
        events.removeIf(e -> e.getId() == id);
        fileHandler.writeList(events);
    }
}