package com.example.beautyApp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class SessionId implements Serializable {
    private String token;

    private Integer userId;

    // default constructor

    public SessionId(String token, Integer userId) {
        this.token = token;
        this.userId = userId;
    }

    // equals() and hashCode()
}