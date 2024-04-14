package com.example.beautyApp.controller;

import com.example.beautyApp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.beautyApp.manager.ServiceProfileManager;
import com.example.beautyApp.model.Portfolio;
import com.example.beautyApp.model.Pricing;
import com.example.beautyApp.model.Review;
import com.example.beautyApp.model.ServiceProfile;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
// import java.util.List;

@RestController
@RequestMapping("/api/serviceProfile")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceProfileController {
    private static final Logger log = LoggerFactory.getLogger(ServiceProfileController.class);

    @Autowired
    private ServiceProfileManager serviceProfileManager;

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceProfile> getServiceProfileById(@PathVariable("serviceId") int serviceId) {
        Optional<ServiceProfile> serviceProfile = serviceProfileManager.getServiceProfileById(serviceId);
        if (serviceProfile.isPresent()) {
            return ResponseEntity.ok(serviceProfile.get());
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateServiceProfile(@RequestBody ServiceProfile serviceProfile) {
        try {
            log.info("************************** updating");
            serviceProfileManager.updateServiceProfile(serviceProfile);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/find")
    public ResponseEntity<Optional<ServiceProfile>> findServiceId(@RequestBody ServiceProfile serviceProfile) {
        Optional<ServiceProfile> serviceProfile2 = serviceProfileManager.findServiceId(serviceProfile);
        if (serviceProfile2.isPresent()) {
            return new ResponseEntity<>(serviceProfile2, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



// Endpoint to insert a new service profile
    @PostMapping("/add")
    public ResponseEntity<ServiceProfile> addServiceProfile(@RequestBody ServiceProfileWithPricing combinedData) {
        ServiceProfile serviceProfile = combinedData.getServiceProfile();
        //List<Pricing> pricingList = new ArrayList<>();
        List<Pricing> pricingList = combinedData.getPricingList();
        for (Pricing pricing : pricingList) {
            pricing.setServiceProfile(serviceProfile);
        }
        log.info("Received new service profile: {}", combinedData.toString());
        log.info("Received new service profile: {}", serviceProfile);
        log.info("Received new service profile pricing: {}", pricingList);

        ServiceProfile savedServiceProfile = serviceProfileManager.saveServiceProfile(serviceProfile, pricingList);
        if (savedServiceProfile != null) {
            return new ResponseEntity<>(savedServiceProfile, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{serviceId}/portfolio")
    public ResponseEntity<List<Portfolio>> getAllImagesByServiceId(@PathVariable("serviceId") int serviceId) {
        List<Portfolio> images = serviceProfileManager.getAllImagesByServiceId(serviceId);
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/{serviceId}/portfolioLogo")
    public ResponseEntity<List<Portfolio>> getFirstLogoByServiceId(@PathVariable("serviceId") int serviceId) {
        List<Portfolio> images = serviceProfileManager.getFirstLogoByServiceId(serviceId);
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/{serviceId}/portfolioImages")
    public ResponseEntity<List<Portfolio>> getPortfolioImagesByServiceId(@PathVariable("serviceId") int serviceId) {
        List<Portfolio> images = serviceProfileManager.getPortfolioImagesByServiceId(serviceId);
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/{serviceId}/pricing")
    public ResponseEntity<List<Pricing>> getAllPricingsByServiceId(@PathVariable("serviceId") int serviceId) {
        List<Pricing> pricings = serviceProfileManager.getAllPricingsByServiceId(serviceId);
        if (!pricings.isEmpty()) {
            return ResponseEntity.ok(pricings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{serviceId}/review")
    public ResponseEntity<List<Review>> getAllReviewsByServiceId(@PathVariable("serviceId") int serviceId) {
        List<Review> reviews = serviceProfileManager.getAllReviewsByServiceId(serviceId);
        if (!reviews.isEmpty()) {
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getServiceList")
    public ResponseEntity<List<ServiceProfile>> getServiceList(@RequestParam int userId) {
        log.info("User ID: " + userId);
        List<ServiceProfile> serviceProfile = serviceProfileManager.getServiceList(userId);
        if (!serviceProfile.isEmpty()) {
            return new ResponseEntity<>(serviceProfile, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/delete")
    public ResponseEntity<String> deleteService(@RequestParam int userId, @RequestParam int serviceId) {
        try {
            log.info("ID: " + serviceId);
            serviceProfileManager.deleteService(userId, serviceId);
            return ResponseEntity.status(HttpStatus.OK).body("Service with ID " + serviceId + " deleted successfully");
        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getServiceStatus")
    public ResponseEntity<Map<String, String>> getServiceStatus(@RequestParam int serviceId) {
        log.info("ID: " + serviceId);
        Optional<Availability> s = serviceProfileManager.getServiceStatus(serviceId);
        log.info(String.valueOf(s));

        if (s.isPresent()) {
            log.info(s.get().getAvailabilityStatus());
            Map<String, String> response = new HashMap<>();
            response.put("availabilityStatus", s.get().getAvailabilityStatus());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/updateServiceStatus")
    public ResponseEntity<String> updateServiceStatus(@RequestParam int serviceId) {
        try {
            log.info("ID: " + serviceId);
            serviceProfileManager.updateServiceStatus(serviceId);
            return ResponseEntity.status(HttpStatus.OK).body("Service with ID " + serviceId + " updated successfully");
        }
            catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/portfolio/{photoId}")
    public ResponseEntity<String> deletePortfolioPhoto(@PathVariable("photoId") int photoId) {
        try {
            log.info(String.valueOf(photoId));
            serviceProfileManager.deletePortfolioPhoto(photoId);
            return ResponseEntity.ok("Portfolio photo deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete portfolio photo");
        }
    }
}