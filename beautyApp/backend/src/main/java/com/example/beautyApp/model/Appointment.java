package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name = "appointment") 
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int appointmentId;

    @Column(name = "appointment_user_id")
    private int appointmentUserId;

    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name = "appointment_user_id", referencedColumnName = "user_id")
    // private User user;

    @Column(name = "appointment_service_id")
    private int appointmentServiceId;

    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name = "appointment_service_id", referencedColumnName = "service_id")
    // private ServiceProfile serviceProfile;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;



    // Getters and Setters

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    // public int getAppointmentUserId() {
    //     return appointmentUserId;
    // }

    // public void setAppointmentUserId(int appointmentUserId) {
    //     this.appointmentUserId = appointmentUserId;
    // }

    // public int getAppointmentServiceId() {
    //     return appointmentServiceId;
    // }

    // public void setAppointmentServiceId(int appointmentServiceId) {
    //     this.appointmentServiceId = appointmentServiceId;
    // }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

}