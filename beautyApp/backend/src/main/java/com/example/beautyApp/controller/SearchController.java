package com.example.beautyApp.controller;

import com.example.beautyApp.manager.SearchManager;
import com.example.beautyApp.model.ServiceProfile;
import com.example.beautyApp.model.TB_User;
import com.example.beautyApp.request.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private SearchManager searchManager;

    // @GetMapping // Handles GET requests to /api/search
    // public ResponseEntity<List<Search>> getAllSearchs() {
    //     // Retrieves all service profiles from the manager
    //     List<Search> searchs = searchManager.getAllSearchs();
    //     // Returns the retrieved service profiles in the response with HTTP status 200 (OK)
    //     return ResponseEntity.ok(searchs);
    // }

    // @GetMapping("/{serviceId}")
    // public ResponseEntity<Search> getSearchById(@PathVariable("serviceId") int serviceId) {
    //     Optional<Search> search = searchManager.getSearchById(serviceId);
    //     if (search.isPresent()) {
    //         return ResponseEntity.ok(search.get());
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    @GetMapping("/{serviceType}")
    public ResponseEntity<List<ServiceProfile>> getSearchByType(@PathVariable("serviceType") String type) {
        List<ServiceProfile> searchByType = searchManager.searchByType(type);
        if (!searchByType.isEmpty()) {
            return ResponseEntity.ok(searchByType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping(path = "/{serviceType}")
    // public ResponseEntity<?> search(@PathVariable("serviceType") String serviceType, @RequestBody String serviceName) throws Exception {
    //     Optional<Search> search = searchManager.search(serviceType, serviceName);
    //     System.out.println("serviceType: " + serviceType);
    //     System.out.println("serviceName: " + serviceName);
    //     if (search.isPresent()) {
    //         return ResponseEntity.ok(search.get());
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }

    // }

    // @GetMapping(path = "/search/{serviceName}")
    // public ResponseEntity<List<ServiceProfile>> search(@PathVariable("serviceProfile") ServiceProfile serviceProfile) throws Exception {
    //     List<ServiceProfile> search = searchManager.search(serviceProfile.getServiceName(), serviceProfile.getServiceType());
    //     System.out.println("serviceType: " + serviceProfile.getServiceType());
    //     System.out.println("serviceName: " +  serviceProfile.getServiceName());
    //     System.out.println(search);
    //     if (!search.isEmpty()) {
    //         return ResponseEntity.ok(search);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @GetMapping(path = "/{serviceType}/search/{serviceName}")
    public ResponseEntity<List<ServiceProfile>> search(@PathVariable("serviceType") String serviceType,
                                                    @PathVariable("serviceName") String serviceName) throws Exception {
        // Assuming searchManager has a method to search by both service type and service name
        List<ServiceProfile> search = searchManager.searchByServiceTypeAndServiceName(serviceType, serviceName);
        // System.out.println("serviceType: " + serviceType);
        // System.out.println("serviceName: " + serviceName);
        // System.out.println(search);
        if (!search.isEmpty()) {
            return ResponseEntity.ok(search);
        } else {
            return ResponseEntity.ok(null);
        }
    }


}