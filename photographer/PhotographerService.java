// PhotographerService.java - Dummy content for scaffolding
package com.eventphotography.service;

import com.eventphotography.model.Photographer;
import com.eventphotography.util.FileHandler;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotographerService {
    private final FileHandler<Photographer> fileHandler;
    private final List<Photographer> photographers;

    public PhotographerService() {
        this.fileHandler = new FileHandler<>("photographers.txt");
        this.photographers = new ArrayList<>();
        loadPhotographers(); // Load from file if needed
    }

    private void loadPhotographers() {
        try {
            System.out.println("Loading photographers...");
            List<String> lines = fileHandler.readLines();
            System.out.println("Read lines: " + lines);
            for (String line : lines) {
                if (line.trim().isEmpty() || line.trim().equals("[]")) {
                    continue;
                }
                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String email = parts[2].trim();
                        double rating = Double.parseDouble(parts[3].trim());
                        photographers.add(new Photographer(id, name, email, rating));
                        System.out.println("Added photographer: " + name + " (" + rating + ")");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Skip invalid lines
                    System.err.println("Skipping invalid photographer line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading photographers: " + e.getMessage());
        }
    }

    // CRUD Operations
    public void addPhotographer(Photographer photographer) throws IOException {
        photographers.add(photographer);
        fileHandler.writeList(photographers);
    }

    public List<Photographer> getAllPhotographers() {
        return photographers;
    }

    public Photographer getPhotographerById(int id) {
        return photographers.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void updatePhotographer(Photographer updatedPhotographer) throws IOException {
        for (int i = 0; i < photographers.size(); i++) {
            if (photographers.get(i).getId() == updatedPhotographer.getId()) {
                photographers.set(i, updatedPhotographer);
                break;
            }
        }
        fileHandler.writeList(photographers);
    }

    public void deletePhotographer(int id) throws IOException {
        photographers.removeIf(p -> p.getId() == id);
        fileHandler.writeList(photographers);
    }

    // Bubble Sort by Rating (Descending Order)
    public void bubbleSortByRating() throws IOException {
        int n = photographers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (photographers.get(j).getRating() < photographers.get(j + 1).getRating()) {
                    Photographer temp = photographers.get(j);
                    photographers.set(j, photographers.get(j + 1));
                    photographers.set(j + 1, temp);
                }
            }
        }
        fileHandler.writeList(photographers);
    }
}