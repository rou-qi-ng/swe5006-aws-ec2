package com.example.beautyApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name="referral")
public class TB_Referral {
    @Id
    @NonNull
    @Column(name = "service_id")
    private Integer serviceId;

    @NonNull
    @Column(name = "code")
    private String code;

}
