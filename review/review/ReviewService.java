package com.eventphotography.service;

import com.eventphotography.model.Review;
import com.eventphotography.util.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    private final FileHandler<Review> fileHandler;
    private List<Review> reviews;

    public ReviewService() {
        this.fileHandler = new FileHandler<>("reviews.txt");
        this.reviews = new ArrayList<>();
        loadReviews();
    }

    private void loadReviews() {
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
                        int photographerId = Integer.parseInt(parts[1].trim());
                        int userId = Integer.parseInt(parts[2].trim());
                        double rating = Double.parseDouble(parts[3].trim());
                        String comment = parts[4].trim();
                        reviews.add(new Review(id, photographerId, userId, rating, comment));
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Skipping invalid review line: " + line);
                }
            }

            // Bubble sort reviews by rating in descending order
            sortReviewsByRatingDescending();

        } catch (IOException e) {
            System.err.println("Error loading reviews: " + e.getMessage());
        }
    }

    //
    private void sortReviewsByRatingDescending() {
        int n = reviews.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (reviews.get(j).getRating() < reviews.get(j + 1).getRating()) {
                    // Swap
                    Review temp = reviews.get(j);
                    reviews.set(j, reviews.get(j + 1));
                    reviews.set(j + 1, temp);
                }
            }
        }
    }

    public void addReview(Review review) throws IOException {
        reviews.add(review);
        sortReviewsByRatingDescending(); // sort after add
        fileHandler.writeList(reviews);
    }

    public List<Review> getAllReviews() {
        return reviews;
    }

    public Review getReviewById(int id) {
        return reviews.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public void updateReview(Review updatedReview) throws IOException {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getId() == updatedReview.getId()) {
                reviews.set(i, updatedReview);
                break;
            }
        }
        sortReviewsByRatingDescending(); // sort after update
        fileHandler.writeList(reviews);
    }

    public void deleteReview(int id) throws IOException {
        reviews.removeIf(r -> r.getId() == id);
        fileHandler.writeList(reviews);
    }
}
