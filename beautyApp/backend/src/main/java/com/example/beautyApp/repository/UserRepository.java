package com.example.beautyApp.repository;

import com.example.beautyApp.model.TB_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<TB_User, Integer> {
    List<TB_User> findAll();

    @Query("SELECT u FROM TB_User u ORDER BY u.userId DESC LIMIT 1")
    TB_User findLastUser();

    Optional<TB_User> findByUsername(String username);

    @Query("SELECT u.username FROM TB_User u WHERE u.userId = :userId")
    Optional<String> findUsernameById(@Param("userId") int id);

    @Query("SELECT u FROM TB_User u WHERE (u.username = :username) AND (u.password = :password)")
    Optional<TB_User> findUser(@Param("username") String username,
                               @Param("password") String password);

    
}
