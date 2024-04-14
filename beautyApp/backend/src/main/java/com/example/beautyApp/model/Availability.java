package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name = "availability") 
public class Availability implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    // @NonNull                         // not needed as int in java will not hold null values
    @Column(name = "availability_id")
    private int availabilityId;


    @Column(name = "availability_service_id")
    private int availabilityServiceId;

    @NonNull
    @Column(name = "availability_status")
    private String availabilityStatus;


    // Getters and Setters

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    // Getter and Setter for service
    public int getAvailabilityServiceId() {
        return availabilityServiceId;
    }

    public void setAvailabilityServiceId(int availabilityServiceId) {
        this.availabilityServiceId = availabilityServiceId;
    }

    // Getter and Setter for status
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}