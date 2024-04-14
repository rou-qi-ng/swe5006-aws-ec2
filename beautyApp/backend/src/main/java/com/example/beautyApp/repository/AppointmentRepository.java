package com.example.beautyApp.repository;

import com.example.beautyApp.model.Appointment;
import com.example.beautyApp.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    void deleteByAppointmentServiceId(int appointmentServiceId);


}
