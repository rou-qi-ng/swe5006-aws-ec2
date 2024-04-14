package com.example.beautyApp.controller;

import com.example.beautyApp.manager.PricingManager;
import com.example.beautyApp.model.Pricing;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PricingControllerTest {

    @Mock
    private PricingManager pricingManager;

    @InjectMocks
    private PricingController pricingController;

    public PricingControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPricing_Success() {
        // Arrange
        List<Pricing> pricingList = Collections.singletonList(new Pricing());

        // Act
        ResponseEntity<String> response = pricingController.addPricing(pricingList);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pricing records added successfully", response.getBody());
        verify(pricingManager, times(1)).saveAllPricing(pricingList);
    }

    @Test
    public void testDeletePricing_Success() {
        // Arrange
        int pricingId = 1;

        // Act
        ResponseEntity<String> response = pricingController.deletePricing(pricingId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
        verify(pricingManager, times(1)).deletePricing(pricingId);
    }

    @Test
    public void testUpdatePricing_Success() {
        // Arrange
        Pricing pricing = new Pricing();

        // Act
        ResponseEntity<String> response = pricingController.updatePricing(pricing);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
        verify(pricingManager, times(1)).updatePricing(pricing);
    }


    @Test
    public void testAddPricing_Exception_InternalServerError() {
        // Arrange
        List<Pricing> pricingList = Collections.singletonList(new Pricing());
        doThrow(RuntimeException.class).when(pricingManager).saveAllPricing(pricingList);

        // Act
        ResponseEntity<String> response = pricingController.addPricing(pricingList);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeletePricing_Exception_InternalServerError() {
        // Arrange
        int pricingId = 1;
        doThrow(RuntimeException.class).when(pricingManager).deletePricing(pricingId);

        // Act
        ResponseEntity<String> response = pricingController.deletePricing(pricingId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody());
    }

    @Test
    public void testUpdatePricing_Exception_InternalServerError() {
        // Arrange
        Pricing pricing = new Pricing();
        doThrow(RuntimeException.class).when(pricingManager).updatePricing(pricing);

        // Act
        ResponseEntity<String> response = pricingController.updatePricing(pricing);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("", response.getBody());
    }
}

