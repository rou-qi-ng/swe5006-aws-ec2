package com.example.beautyApp.repository;

import com.example.beautyApp.model.TB_Referral;
import com.example.beautyApp.model.TB_Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends JpaRepository<TB_Referral, Integer> {

}
