package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name = "pricing") 
public class Pricing implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @NonNull                         // not needed as int in java will not hold null values
    @Column(name = "pricing_id")
    private int pricingId;


    // @Column(name = "pricing_service_id") 
    // private int pricingServiceId;

    @NonNull
    @Column(name = "pricing_name")
    private String pricingName;


    @Column(name = "pricing_cost")
    private int pricingCost;

    @NonNull
    @Column(name = "pricing_addon")
    private String pricingAddon;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pricing_service_id", referencedColumnName = "service_id")
    private ServiceProfile serviceProfile;


    // Getters and setters

    public int getPricingId() {
        return pricingId;
    }

    public void setPricingId(int pricingId) {
        this.pricingId = pricingId;
    }

    public ServiceProfile getServiceProfile() {
        return serviceProfile;
    }

    // Setter for serviceProfile
    public void setServiceProfile(ServiceProfile serviceProfile) {
        this.serviceProfile = serviceProfile;
    }

    public String getPricingName() {
        return pricingName;
    }

    public void setPricingName(String pricingName) {
        this.pricingName = pricingName;
    }

    public int getPricingCost() {
        return pricingCost;
    }

    public void setPricingCost(int pricingCost) {
        this.pricingCost = pricingCost;
    }

    public String getPricingAddon() {
        return pricingAddon;
    }

    public void setPricingAddon(String pricingAddon) {
        this.pricingAddon = pricingAddon;
    }

}




