package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name="service")
public class TB_Service {
    @Id
    @NonNull
    @Column(name = "service_id")
    private Integer serviceId;

    @NonNull
    @Column(name = "location")
    private String location;

    @NonNull
    @Column(name = "type")
    private String type;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "name")
    private String name;


    @Lob
    @Column(name = "profile")
    private byte[] profile;


//    public TB_Service() {
//    }
//
//    public TB_Service(Integer serviceId, String location, String type, String description, String name, byte[] profile) {
//        this.serviceId = serviceId;
//        this.location = location;
//        this.type = type;
//        this.description = description;
//        this.name = name;
//        this.profile = profile;
//    }

}
