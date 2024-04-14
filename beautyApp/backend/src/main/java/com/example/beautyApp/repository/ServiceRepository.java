package com.example.beautyApp.repository;

import com.example.beautyApp.model.TB_Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<TB_Service, Integer> {
    TB_Service findByServiceId(Integer id);
}
