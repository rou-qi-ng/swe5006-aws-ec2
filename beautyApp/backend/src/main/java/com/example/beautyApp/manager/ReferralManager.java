package com.example.beautyApp.manager;

import com.example.beautyApp.model.TB_User;
import com.example.beautyApp.repository.ReferralRepository;
import com.example.beautyApp.repository.UserRepository;
import com.example.beautyApp.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReferralManager {
    @Autowired
    private ReferralRepository referralRepository;

    public String getCode(String username) {
//        Optional<TB_User> user= userRepository.findUser(loginRequest.getUsername(),loginRequest.getPassword());
//        System.out.println(user);
        return "testcode";

    }
}
