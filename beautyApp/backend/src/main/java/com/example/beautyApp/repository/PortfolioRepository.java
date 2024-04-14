package com.example.beautyApp.repository;
import com.example.beautyApp.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PortfolioRepository extends JpaRepository<Portfolio, String>{
    void deleteByPortfolioServiceId(int serviceId);
    void deleteByPortfolioId(int serviceId);
    List<Portfolio> findByPortfolioServiceId(int serviceId);
}
