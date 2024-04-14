package com.example.beautyApp.model;
import jakarta.persistence.*; // for Spring Boot 3
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name="user")
public class TB_User implements Serializable {


    @Id
    @NonNull
    @Column(name = "user_id")
    private Integer userId;

    @NonNull
    @Column(name = "user_name")
    private String username;

    @NonNull
    @Column(name = "user_password")
    private String password;

    @NonNull
    @Column(name = "user_type")
    private String userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}




