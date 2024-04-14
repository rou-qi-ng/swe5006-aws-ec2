package com.example.beautyApp.repository;

import com.example.beautyApp.model.ServiceProfile;
// import com.example.beautyApp.model.User;
//import com.example.beautyApp.model.User;

import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
public interface SearchRepository extends JpaRepository<ServiceProfile, Integer> {
    // List<Search> findAll();

    // Optional<Search> findByServiceId(int serviceId);

    List<ServiceProfile> findByServiceType(String type);

    // @Query("SELECT u FROM Search u WHERE ((u.serviceName = :serviceName) AND (u.type = :serviceType))")
    // Optional<Search> findServiceName( @Param("serviceType") String serviceType,
    //                                           @Param("serviceName") String serviceName);

    // @Query("SELECT sp FROM ServiceProfile sp WHERE sp.serviceName LIKE %:serviceName% AND sp.serviceType = :serviceType")
    // List<ServiceProfile> findByNameAndType(@Param("serviceName") String serviceName, @Param("serviceType") String serviceType);

    @Query("SELECT sp FROM ServiceProfile sp WHERE sp.serviceName LIKE %:serviceName% AND sp.serviceType = :serviceType")
    List<ServiceProfile> findByServiceTypeAndServiceName(@Param("serviceType") String serviceType, @Param("serviceName") String serviceName);




    // List<ServiceProfile> findByServiceTypeAndServiceName(String serviceType, String serviceName);
}
