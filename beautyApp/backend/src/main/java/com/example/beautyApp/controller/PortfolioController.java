package com.example.beautyApp.controller;

import com.example.beautyApp.manager.PortfolioManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioController {

    @Autowired
    private PortfolioManager portfolioManager;
    private static final Logger log = LoggerFactory.getLogger(PortfolioController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("serviceId") int serviceId, @RequestParam("data") MultipartFile file) {
        try {
            if (String.valueOf(serviceId) != null) {
                log.info("Service ID: {}", serviceId);
                portfolioManager.saveFile(serviceId, file);
                return ResponseEntity.ok("File uploaded successfully");
            }
            else {
                throw new IllegalArgumentException("Service ID cannot be zero");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid service ID");
        }
    }

}
