package com.example.beautyApp.repository;

import com.example.beautyApp.model.TB_User;
import com.example.beautyApp.model.TB_UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<TB_UserSession, Integer> {

    @Query("SELECT u FROM TB_UserSession u WHERE (u.token = :token)")
    Optional<TB_UserSession> findByToken(@Param("token") String token);

    @Query("SELECT u.userId FROM TB_UserSession u WHERE u.token = :token")
    Optional<Integer> findUserIdByToken(@Param("token") String token);

}
