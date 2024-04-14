package com.example.beautyApp.repository;

import com.example.beautyApp.model.Review;
import com.example.beautyApp.model.ServiceProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    void deleteByServiceProfile(ServiceProfile serviceProfile);
    
}
