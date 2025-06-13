package com.contractormanagemet.Contractor.Magement.DTO;

import java.util.List;

public class PurchaserWithTransactionsDto {
    private Long id;
    private String name;

    private String phoneNumber;

    private String address;
    private String aadharNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
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

    @Override
    public String toString() {
        return "PurchaserWithTransactionsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", total=" + total +
                ", landTransactions=" + landTransactions +
                '}';
    }
}
