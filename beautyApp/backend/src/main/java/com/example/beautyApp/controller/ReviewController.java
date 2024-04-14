package com.example.beautyApp.controller;

import com.example.beautyApp.manager.ReviewManager;
import com.example.beautyApp.model.Review;
import com.example.beautyApp.model.ServiceProfile;
import com.example.beautyApp.repository.ServiceProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/serviceProfile")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewManager reviewManager;

    @Autowired
    private ServiceProfileRepository serviceProfileRepository;

    // @GetMapping("/{serviceId}/review")
    // public ResponseEntity<List<Review>> getAvailabilitiesById(@PathVariable("serviceId") int serviceId) {
    //     List<Review> availabilities = reviewManager.getAvailabilitiesById(serviceId);
    //     if (!availabilities.isEmpty()) {
    //         return ResponseEntity.ok(availabilities);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @GetMapping("/{serviceId}/review")
    // public ResponseEntity<List<Review>> getReviewsById(@PathVariable("serviceId") int serviceId) {
    //     List<Review> reviews = reviewManager.getReviewsById(serviceId);
    //     if (!reviews.isEmpty()) {
    //         return ResponseEntity.ok(reviews);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    @PostMapping("/{serviceId}/review/new")
    public ResponseEntity<?> addReview(@PathVariable("serviceId") int serviceId, @RequestBody Review reviewData) {
        try {
            // Fetch the ServiceProfile entity corresponding to the serviceId
            ServiceProfile serviceProfile = serviceProfileRepository.findById(serviceId).orElse(null);
            if (serviceProfile == null) {
                return ResponseEntity.notFound().build(); // Return 404 if service profile not found
            }
            
            // Set the serviceProfile in the reviewData
            reviewData.setServiceProfile(serviceProfile);
            
            // Save the reviewData
            reviewManager.save(reviewData);
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}