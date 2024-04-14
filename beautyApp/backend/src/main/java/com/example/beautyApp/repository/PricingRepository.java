package com.example.beautyApp.repository;

import com.example.beautyApp.model.Pricing;
import com.example.beautyApp.model.ServiceProfile;
import com.example.beautyApp.model.TB_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Integer> {
    @Query("SELECT u FROM Pricing u ORDER BY u.pricingId DESC LIMIT 1")
    Pricing findLastPrice();

    void deleteByServiceProfile(ServiceProfile serviceProfile);
}
