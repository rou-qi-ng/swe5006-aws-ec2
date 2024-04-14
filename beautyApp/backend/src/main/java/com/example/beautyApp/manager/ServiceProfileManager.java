package com.example.beautyApp.manager;

import com.example.beautyApp.model.Portfolio;
import com.example.beautyApp.model.*;
import com.example.beautyApp.repository.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceProfileManager {
    @Autowired
    private ServiceProfileRepository serviceProfileRepository;
    @Autowired
    private PricingRepository pricingRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private final AvailabilityRepository availabilityRepository;

    private static final Logger log = LoggerFactory.getLogger(ServiceProfileManager.class);

    public ServiceProfileManager(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }


    // public List<ServiceProfile> getAllServiceProfiles() {
    //     return serviceProfileRepository.findAll();
    // }

    public Optional<ServiceProfile> getServiceProfileById(int serviceId) {
        return serviceProfileRepository.findByServiceId(serviceId);
    }

    public Optional<ServiceProfile> findServiceId(ServiceProfile s) {
        return serviceProfileRepository.findByServiceLocationAndServiceTypeAndServiceDescriptionAndServiceName(s.getServiceLocation(), s.getServiceType(), s.getServiceDescription(), s.getServiceName());
    }

    public ServiceProfile saveServiceProfile(ServiceProfile serviceProfile,  List<Pricing> pricingList) {
        ServiceProfile newService  = serviceProfileRepository.save(serviceProfile);
        log.info(String.valueOf(newService));
        if (pricingList != null){
            for (Pricing price : pricingList ){
                if (price.getPricingName() != "" && price.getPricingName() !=null ){
                    price.getServiceProfile().setServiceId(newService.getServiceId());
//                    log.info(String.valueOf(price));
                    Integer lastPriceId= pricingRepository.findLastPrice().getPricingId();
                    price.getServiceProfile().setServiceId(newService.getServiceId());
                    price.setPricingId(lastPriceId + 1);
                    log.info(String.valueOf(price));
//                    TB_User saveUser = userRepository.save(preSaveUser);
                    pricingRepository.save(price);
                }
                else{
                    System.out.println("this was skipped");
                    log.info(String.valueOf(price));
                }
            }
        }
        Integer lastAvailabilityId = availabilityRepository.findLastAvailabilityId();
        log.info("id is here "+String.valueOf(lastAvailabilityId));
        Availability availability = new Availability();
        availability.setAvailabilityId(lastAvailabilityId != null ? lastAvailabilityId + 1 : 1);
        availability.setAvailabilityServiceId(newService.getServiceId());
        availability.setAvailabilityStatus("Y");
        availabilityRepository.save(availability);
        return newService;
    }


    public List<Portfolio> getAllImagesByServiceId(int serviceId) {
        return serviceProfileRepository.findImagesByServiceId(serviceId);
    }

    public List<Portfolio> getFirstLogoByServiceId(int serviceId) {
        return serviceProfileRepository.findFirstLogoByServiceId(serviceId);
    }

    public List<Portfolio> getPortfolioImagesByServiceId(int serviceId) {
        return serviceProfileRepository.findPortfolioImagesByServiceId(serviceId);
    }
    
    public List<Pricing> getAllPricingsByServiceId(int serviceId) {
        return serviceProfileRepository.findPricingsByServiceId(serviceId);
    }

    public List<Review> getAllReviewsByServiceId(int serviceId) {
        return serviceProfileRepository.findReviewsByServiceId(serviceId);
    }

    public List<ServiceProfile> getServiceList(int userId) {
        return serviceProfileRepository.findServiceProfilesByUserId(userId);
    }

    public Optional<Availability> getServiceStatus(int serviceId) {
        return availabilityRepository.findFirstByAvailabilityServiceId(serviceId);
    }

    @Transactional
    public void updateServiceStatus(int serviceId) {
        Optional<Availability> a = availabilityRepository.findFirstByAvailabilityServiceId(serviceId);
        log.info(a.get().getAvailabilityStatus());
        if (a.isPresent() && Objects.equals(a.get().getAvailabilityStatus(), "N"))
            availabilityRepository.updateAvailabilityStatusById(serviceId, "Y");
        else {
            availabilityRepository.updateAvailabilityStatusById(serviceId, "N");
        }
    }

    @Transactional
    public void deleteService(int userId, int serviceId) {
        BusinessId businessId = new BusinessId(userId, serviceId);
        Optional<ServiceProfile> newService  = serviceProfileRepository.findByServiceId(serviceId);
        log.info("rches here");
        appointmentRepository.deleteByAppointmentServiceId(serviceId);
        log.info("done0");
        availabilityRepository.deleteByAvailabilityServiceId(serviceId);
        log.info("done1");
        businessRepository.deleteById(businessId);
        log.info("done2");
        portfolioRepository.deleteByPortfolioServiceId(serviceId);
        log.info("done3");
        if (newService.isPresent()){
            reviewRepository.deleteByServiceProfile(newService.get());
            log.info("done4");
            pricingRepository.deleteByServiceProfile(newService.get());
            log.info("done5");
        }
        serviceProfileRepository.deleteById(serviceId);
        log.info("done");
    }

    @Transactional
    public void updateServiceProfile(ServiceProfile serviceProfile) {
        serviceProfileRepository.save(serviceProfile);
    }

    @Transactional
    public List<Portfolio> getAllPortfolioByServiceId(int serviceId) {
        return portfolioRepository.findByPortfolioServiceId(serviceId);
    }

    @Transactional
    public void deletePortfolioPhoto(int photoId) {
        portfolioRepository.deleteByPortfolioId(photoId);
    }
}
