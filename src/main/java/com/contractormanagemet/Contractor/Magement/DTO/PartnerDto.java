package com.contractormanagemet.Contractor.Magement.DTO;

import java.util.List;

public class PartnerDto {

    private Long id;
    private String name;
    private String city;
    private String phoneNumber;
    private String amount;
    private String paymentDate;
    private List<LandTransactionDto> landTransactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<LandTransactionDto> getLandTransactions() {
        return landTransactions;
    }

    public void setLandTransactions(List<LandTransactionDto> landTransactions) {
        this.landTransactions = landTransactions;
    }
}
