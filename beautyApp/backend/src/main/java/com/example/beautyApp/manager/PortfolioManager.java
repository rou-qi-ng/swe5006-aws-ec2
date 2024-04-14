package com.example.beautyApp.manager;
import java.io.IOException;
import java.util.stream.Stream;

import com.example.beautyApp.controller.PortfolioController;
import com.example.beautyApp.model.Portfolio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.beautyApp.repository.PortfolioRepository;

@Service
public class PortfolioManager {
    private static final Logger log = LoggerFactory.getLogger(PortfolioManager.class);

    @Autowired
    private PortfolioRepository  portfolioRepository ;

    public void saveFile(Integer serviceId, MultipartFile file) throws IOException {
        log.info("Service ID: {}", serviceId);
        Portfolio portfolio = new Portfolio();
        if (serviceId != null) {
            portfolio.setPortfolioServiceId(serviceId);
            log.info("Service ID: {}", portfolio.getPortfolioServiceId());
            portfolio.setPortfolioData(file.getBytes());
            portfolioRepository.save(portfolio);
        } else {
            portfolio.setPortfolioServiceId(0);
        }

    }
}
