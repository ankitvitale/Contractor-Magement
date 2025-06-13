package com.contractormanagemet.Contractor.Magement.DTO;

import java.util.List;

public class PartnerWithTransactionsDto {
    private Long id;
    private String name;
    private String city;
    private String phoneNumber;
 //   private Double amount;
    private String paymentDate;
    private Double total;
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

//    public Double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<LandTransactionDto> getLandTransactions() {
        return landTransactions;
    }

    public void setLandTransactions(List<LandTransactionDto> landTransactions) {
        this.landTransactions = landTransactions;
    }
}
