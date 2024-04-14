package com.example.beautyApp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ServiceProfileWithPricing {
    private ServiceProfile serviceProfile;
    private List<Pricing> pricingList;

    public ServiceProfileWithPricing(ServiceProfile serviceProfile, List<Pricing> pricingList) {
        this.serviceProfile = serviceProfile;
        this.pricingList = pricingList;
    }

    public ServiceProfile getServiceProfile() {
        return serviceProfile;
    }

    public void setServiceProfile(ServiceProfile serviceProfile) {
        this.serviceProfile = serviceProfile;
    }

    @OneToMany(mappedBy = "serviceProfile", cascade = CascadeType.ALL)
    public List<Pricing> getPricingList() {
        return pricingList;
    }

    public void setPricingList(List<Pricing> pricingList) {
        this.pricingList = pricingList;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServiceProfile: ").append(serviceProfile).append("\n");
        sb.append("PricingList: ").append(pricingList);
        return sb.toString();
    }
}

