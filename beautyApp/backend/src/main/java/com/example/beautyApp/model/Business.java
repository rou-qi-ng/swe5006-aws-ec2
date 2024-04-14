package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@IdClass(BusinessId.class)
@Table(name = "business")
public class Business {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "service_id")
    private int serviceId;

    // Define relationships
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private TB_User user;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    private ServiceProfile service;

    public Business(int userId, int serviceId) {
        this.userId = userId;
        this.serviceId = serviceId;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public TB_User getUser() {
        return user;
    }

    public void setUser(TB_User user) {
        this.user = user;
    }

    public ServiceProfile getService() {
        return service;
    }

    public void setService(ServiceProfile service) {
        this.service = service;
    }
}
