package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name = "review") 
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "review_user_id")
    private int reviewUserId;

    // @Column(name = "review_service_id")
    // private int reviewServiceId;

    @Column(name = "review_rating")
    private int reviewRating;

    @Column(name = "review_description")
    private String reviewDescription;

    @Column(name = "review_created_date")
    private Date reviewCreatedDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "review_service_id", referencedColumnName = "service_id")
    private ServiceProfile serviceProfile;
    

    // Getters and setters

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    // public int getReviewServiceId() {
    //     return reviewServiceId;
    // }

    // public void setReviewServiceId(int reviewServiceId) {
    //     this.reviewServiceId = reviewServiceId;
    // }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Date getReviewCreatedDate() {
        return reviewCreatedDate;
    }

    public void setReviewCreatedDate(Date reviewCreatedDate) {
        this.reviewCreatedDate = reviewCreatedDate;
    }
}
