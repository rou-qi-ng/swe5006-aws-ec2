package com.example.beautyApp.controller;

import com.example.beautyApp.manager.PricingManager;
import com.example.beautyApp.model.Pricing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/pricing")
@CrossOrigin(origins = "http://localhost:4200")
public class PricingController {
    private static final Logger log = LoggerFactory.getLogger(PricingController.class);

    @Autowired
    private PricingManager pricingManager;

    @PostMapping("/add")
    public ResponseEntity<String> addPricing(@RequestBody List<Pricing> pricingList) {
        try {
            log.info("**************************");
            log.info(pricingList.toString());
            pricingManager.saveAllPricing(pricingList);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pricing records added successfully");

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePricing(@RequestBody int pricingId) {
        try {
            log.info("**************************");
            log.info(String.valueOf(pricingId));
            pricingManager.deletePricing(pricingId);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.ok("");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePricing(@RequestBody Pricing pricing) {
        try {
            log.info("**************************");
            log.info(String.valueOf(pricing));
            pricingManager.updatePricing(pricing);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.ok("");
        }
    }
}
