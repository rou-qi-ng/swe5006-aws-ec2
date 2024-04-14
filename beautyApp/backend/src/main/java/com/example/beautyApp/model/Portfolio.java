package com.example.beautyApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Setter
@Getter
@Table(name = "portfolio") 
public class Portfolio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private int portfolioId;

    @Column(name = "portfolio_service_id")
    private int portfolioServiceId;

    @Column(name = "portfolio_data")
    private byte[] portfolioData;

    @Column(name = "portfolio_logo")
    private int portfolioLogo;

    
    // Getters and setters

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public int getPortfolioServiceId() {
        return portfolioServiceId;
    }

    public void setPortfolioServiceId(int portfolioServiceId) {
        this.portfolioServiceId = portfolioServiceId;
    }

    public byte[] getPortfolioData() {
        return portfolioData;
    }

    public void setPortfolioData(byte[] portfolioData) {
        this.portfolioData = portfolioData;
    }

    public int getPortfolioLogo() {
        return portfolioLogo;
    }

    public void setPortfolioLogo(int portfolioLogo) {
        this.portfolioLogo = portfolioLogo;
    }
}
