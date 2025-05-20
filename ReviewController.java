// ReviewController.java - Dummy content for scaffolding
package com.eventphotography.controller;

import com.eventphotography.model.Review;
import com.eventphotography.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviewList";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute Review review) throws IOException {
        reviewService.addReview(review);
        return "redirect:/reviews";
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute Review review) throws IOException {
        reviewService.updateReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id) throws IOException {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}