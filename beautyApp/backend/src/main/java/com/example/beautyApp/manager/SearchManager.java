package com.example.beautyApp.manager;

import com.example.beautyApp.model.ServiceProfile;
//import com.example.beautyApp.model.User;
import com.example.beautyApp.repository.SearchRepository;
import com.example.beautyApp.repository.ServiceProfileRepository;
//import com.example.beautyApp.request.SignUpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchManager {
    @Autowired
    private SearchRepository searchRepository;

    // public List<Search> getAllSearchs() {
    //     return searchRepository.findAll();
    // }

    // public Optional<ServiceProfile> getSearchById(int serviceId) {
    //     return searchRepository.findByServiceId(serviceId);
    // }

    public List<ServiceProfile> searchByType(String type) {
        return searchRepository.findByServiceType(type);
    }
    
    public List<ServiceProfile> searchByServiceTypeAndServiceName(String serviceType, String serviceName) {
        System.out.println("serviceType: " + serviceType);
        System.out.println("serviceName: " + serviceName);
        return searchRepository.findByServiceTypeAndServiceName(serviceType, serviceName);
    }

    // public List<ServiceProfile> search( String serviceName, String serviceType) {
    //     System.out.println("serviceType1: " + serviceType);
    //     System.out.println("serviceName1: " + serviceName);
    //     System.out.println(searchRepository.findByNameAndType(serviceName, serviceType));
    //     return searchRepository.findByNameAndType(serviceName, serviceType);
    //     // if (serviceType.equals("Nails")){
    //     //     return serviceProfileRepository.findServiceName("Nails", serviceName);  
    //     // }
    //     // else if (serviceType.equals("Lash")){
    //     //     return serviceProfileRepository.findServiceName("Lash", serviceName);
    //     // }
    // }
}
