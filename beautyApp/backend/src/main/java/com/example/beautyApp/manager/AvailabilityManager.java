package com.example.beautyApp.manager;

import com.example.beautyApp.model.Availability;
import com.example.beautyApp.model.Appointment;
import com.example.beautyApp.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
// import java.util.Optional;

@Service
public class AvailabilityManager {
    @Autowired
    private AvailabilityRepository availabilityRepository;

    // public List<Availability> getAllAvailabilitys() {
    //     return availabilityRepository.findAll();
    // }

    public List<Availability> getAvailabilitiesById(int serviceId) {
        return availabilityRepository.findByAvailabilityServiceId(serviceId);
    }

    public List<Appointment> getAppointmentsById(int serviceId) {
        return availabilityRepository.findByAppointmentServiceId(serviceId);
    }

    public void save(Appointment appointmentData) {
        availabilityRepository.save(appointmentData);
    }
    
}
