package com.example.beautyApp.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionRequest {
    public interface LoginGroup{}
    private String token;
    private String username;
}
