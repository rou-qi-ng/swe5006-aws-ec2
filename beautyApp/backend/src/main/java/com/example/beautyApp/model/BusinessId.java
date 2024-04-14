package com.example.beautyApp.model;

import java.io.Serializable;
import java.util.Objects;

public class BusinessId implements Serializable {

    private int userId;
    private int serviceId;

    // Constructors, getters, and setters

    public BusinessId() {
    }

    public BusinessId(int userId, int serviceId) {
        this.userId = userId;
        this.serviceId = serviceId;
    }

    // Equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessId that = (BusinessId) o;
        return userId == that.userId && serviceId == that.serviceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, serviceId);
    }
}
