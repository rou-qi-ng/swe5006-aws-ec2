package com.example.beautyApp.repository;

import com.example.beautyApp.model.Business;
import com.example.beautyApp.model.BusinessId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, BusinessId> {
}
