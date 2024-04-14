package com.example.beautyApp.controller;

import com.example.beautyApp.manager.*;
import com.example.beautyApp.model.*;
import com.example.beautyApp.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ServiceProfileControllerTest {

    @Mock
    private ServiceProfileRepository serviceProfileRepository;

    @Mock
    private ServiceProfileManager serviceProfileManager;

    @InjectMocks
    private ServiceProfileController serviceProfileController;
    @Mock
    private PortfolioManager portfolioManager;

    @Mock
    private PortfolioController portfolioController;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetServiceProfileById_Success() {
        // Arrange
        int serviceId = 1;
        ServiceProfile mockServiceProfile = new ServiceProfile();
        when(serviceProfileManager.getServiceProfileById(serviceId)).thenReturn(Optional.of(mockServiceProfile));

        // Act
        ResponseEntity<ServiceProfile> response = serviceProfileController.getServiceProfileById(serviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockServiceProfile, response.getBody());
    }

    @Test
    public void testGetServiceProfileById_NotFound() {
        // Arrange
        int serviceId = 1;
        when(serviceProfileManager.getServiceProfileById(serviceId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ServiceProfile> response = serviceProfileController.getServiceProfileById(serviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testUpdateServiceProfile_Success() {
        // Arrange
        ServiceProfile mockServiceProfile = new ServiceProfile();
        doNothing().when(serviceProfileManager).updateServiceProfile(mockServiceProfile);

        // Act
        ResponseEntity<String> response = serviceProfileController.updateServiceProfile(mockServiceProfile);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    public void testUpdateServiceProfile_Exception_InternalServerError() {
        // Arrange
        ServiceProfile serviceProfile = new ServiceProfile();
        serviceProfile.setServiceId(1); // Set required fields

        // Mock the behavior of serviceProfileManager.updateServiceProfile to throw an exception
        doThrow(RuntimeException.class).when(serviceProfileManager).updateServiceProfile(serviceProfile);

        // Act
        ResponseEntity<String> response = serviceProfileController.updateServiceProfile(serviceProfile);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testFindServiceId_ServiceProfileFound_OK() {
        // Arrange
        ServiceProfile serviceProfile = new ServiceProfile(); // Create a service profile for testing
        when(serviceProfileManager.findServiceId(serviceProfile)).thenReturn(Optional.of(serviceProfile));

        // Act
        ResponseEntity<Optional<ServiceProfile>> response = serviceProfileController.findServiceId(serviceProfile);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceProfile, response.getBody().get());
    }

    @Test
    public void testFindServiceId_ServiceProfileNotFound_NotFound() {
        // Arrange
        ServiceProfile serviceProfile = new ServiceProfile(); // Create a service profile for testing
        when(serviceProfileManager.findServiceId(serviceProfile)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Optional<ServiceProfile>> response = serviceProfileController.findServiceId(serviceProfile);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void testAddServiceProfile_Success() {
        // Arrange
        ServiceProfile serviceProfile = new ServiceProfile(); // Create a service profile for testing
        List<Pricing> pricingList = new ArrayList<>(); // Create a list of pricings
        ServiceProfileWithPricing combinedData = new ServiceProfileWithPricing(serviceProfile, pricingList);
        when(serviceProfileManager.saveServiceProfile(serviceProfile, pricingList)).thenReturn(serviceProfile); // Mock the behavior of serviceProfileManager.saveServiceProfile

        // Act
        ResponseEntity<ServiceProfile> response = serviceProfileController.addServiceProfile(combinedData);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(serviceProfile, response.getBody());
        verify(serviceProfileManager, times(1)).saveServiceProfile(serviceProfile, pricingList); // Verify that the saveServiceProfile method is called once
    }

    @Test
    public void testAddServiceProfile_Failure() {
        // Arrange
        ServiceProfile serviceProfile = new ServiceProfile(); // Create a service profile for testing
        List<Pricing> pricingList = new ArrayList<>(); // Create a list of pricings
        ServiceProfileWithPricing combinedData = new ServiceProfileWithPricing(serviceProfile, pricingList);
        when(serviceProfileManager.saveServiceProfile(serviceProfile, pricingList)).thenReturn(null); // Mock the behavior of serviceProfileManager.saveServiceProfile

        // Act
        ResponseEntity<ServiceProfile> response = serviceProfileController.addServiceProfile(combinedData);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(serviceProfileManager, times(1)).saveServiceProfile(serviceProfile, pricingList); // Verify that the saveServiceProfile method is called once
    }

    @Test
    public void testGetServiceList_NotEmpty() {
        // Arrange
        int userId = 1; // Example user ID
        List<ServiceProfile> serviceProfiles = new ArrayList<>(); // Example list of service profiles
        serviceProfiles.add(new ServiceProfile()); // Adding a service profile for testing
        when(serviceProfileManager.getServiceList(userId)).thenReturn(serviceProfiles); // Mock the behavior of serviceProfileManager.getServiceList

        // Act
        ResponseEntity<List<ServiceProfile>> response = serviceProfileController.getServiceList(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceProfiles, response.getBody());
        verify(serviceProfileManager, times(1)).getServiceList(userId); // Verify that the getServiceList method is called once with the provided userId
    }

    @Test
    public void testGetServiceList_Empty() {
        // Arrange
        int userId = 2; // Example user ID
        List<ServiceProfile> emptyList = new ArrayList<>(); // Empty list
        when(serviceProfileManager.getServiceList(userId)).thenReturn(emptyList); // Mock the behavior of serviceProfileManager.getServiceList

        // Act
        ResponseEntity<List<ServiceProfile>> response = serviceProfileController.getServiceList(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(serviceProfileManager, times(1)).getServiceList(userId); // Verify that the getServiceList method is called once with the provided userId
    }

    @Test
    public void testDeleteService_Success() {
        // Arrange
        int userId = 1; // Example user ID
        int serviceId = 1; // Example service ID

        // Act
        ResponseEntity<String> response = serviceProfileController.deleteService(userId, serviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service with ID " + serviceId + " deleted successfully", response.getBody());
        verify(serviceProfileManager, times(1)).deleteService(userId, serviceId); // Verify that the deleteService method is called once with the provided userId and serviceId
    }

    @Test
    public void testDeleteService_NotFound() {
        // Arrange
        int userId = 2; // Example user ID
        int serviceId = 2; // Example service ID
        doThrow(new RuntimeException()).when(serviceProfileManager).deleteService(userId, serviceId); // Mock the behavior of serviceProfileManager.deleteService to throw an exception

        // Act
        ResponseEntity<String> response = serviceProfileController.deleteService(userId, serviceId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(serviceProfileManager, times(1)).deleteService(userId, serviceId); // Verify that the deleteService method is called once with the provided userId and serviceId
    }

    @Test
    public void testGetServiceStatus_ServiceFound() {
        // Arrange
        int serviceId = 1; // Example service ID
        Availability availability = new Availability();
        availability.setAvailabilityStatus("Available"); // Set up availability status

        when(serviceProfileManager.getServiceStatus(serviceId)).thenReturn(Optional.of(availability)); // Mock the behavior to return Optional with availability object

        // Act
        ResponseEntity<Map<String, String>> response = serviceProfileController.getServiceStatus(serviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Available", response.getBody().get("availabilityStatus")); // Check if the availability status is returned correctly
        verify(serviceProfileManager, times(1)).getServiceStatus(serviceId); // Verify that the getServiceStatus method is called once with the provided serviceId
    }

    @Test
    public void testGetServiceStatus_ServiceNotFound() {
        // Arrange
        int serviceId = 1; // Example service ID

        // Mock the behavior of serviceProfileManager.getServiceStatus to return an empty optional
        doReturn(Optional.empty()).when(serviceProfileManager).getServiceStatus(serviceId);

        // Act
        ResponseEntity<Map<String, String>> response = serviceProfileController.getServiceStatus(serviceId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateServiceStatus_Success() {
        // Arrange
        int serviceId = 1; // Example service ID

        // Mock the behavior of serviceProfileManager.updateServiceStatus to not throw any exceptions
        doNothing().when(serviceProfileManager).updateServiceStatus(serviceId);

        // Act
        ResponseEntity<String> response = serviceProfileController.updateServiceStatus(serviceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service with ID " + serviceId + " updated successfully", response.getBody());
        verify(serviceProfileManager, times(1)).updateServiceStatus(serviceId); // Verify that the updateServiceStatus method is called once with the provided serviceId
    }

    @Test
    public void testUpdateServiceStatus_Exception() {
        // Arrange
        int serviceId = 2; // Example service ID

        // Mock the behavior of serviceProfileManager.updateServiceStatus to throw an exception
        doThrow(RuntimeException.class).when(serviceProfileManager).updateServiceStatus(serviceId);

        // Act
        ResponseEntity<String> response = serviceProfileController.updateServiceStatus(serviceId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(serviceProfileManager, times(1)).updateServiceStatus(serviceId); // Verify that the updateServiceStatus method is called once with the provided serviceId
    }


    @Test
    public void testDeletePortfolioPhoto_Success() {
        // Arrange
        int photoId = 1; // Example photo ID

        // Act
        ResponseEntity<String> response = serviceProfileController.deletePortfolioPhoto(photoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Portfolio photo deleted successfully", response.getBody());
        verify(serviceProfileManager, times(1)).deletePortfolioPhoto(photoId);
    }

    @Test
    public void testDeletePortfolioPhoto_Exception_InternalServerError() {
        // Arrange
        int photoId = 1; // Example photo ID
        doThrow(RuntimeException.class).when(serviceProfileManager).deletePortfolioPhoto(photoId);

        // Act
        ResponseEntity<String> response = serviceProfileController.deletePortfolioPhoto(photoId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to delete portfolio photo", response.getBody());
        verify(serviceProfileManager, times(1)).deletePortfolioPhoto(photoId);
    }

}

