package com.example.beautyApp.manager;

import com.example.beautyApp.model.Review;
import com.example.beautyApp.model.Review;
import com.example.beautyApp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
// import java.util.Optional;

@Service
public class ReviewManager {
    @Autowired
    private ReviewRepository reviewRepository;

    // // public List<Review> getAllReviews() {
    // //     return reviewRepository.findAll();
    // // }

    // public List<Review> getAvailabilitiesById(int serviceId) {
    //     return reviewRepository.findByReviewServiceId(serviceId);
    // }

    // public List<Review> getReviewsById(int serviceId) {
    //     return reviewRepository.findByReviewServiceId(serviceId);
    // }

    public void save(Review reviewData) {
        reviewRepository.save(reviewData);
    }
    
}
