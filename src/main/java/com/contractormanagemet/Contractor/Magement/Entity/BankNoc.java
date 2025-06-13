package com.contractormanagemet.Contractor.Magement.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="bank_noc")
public class BankNoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String bankName;
    private String address;
    private String blank;
    private String coustomername;
    private LocalDate aggrementDate;
    private String flatNo;
    private String buildingNo;
    private String streetNo;
    private String localityName;
    private String areaName;
    private String pincode;
    private String city;
    private String transactionAmount;
    private String transactionAmountWords;
    private String facvoringName;
    private String  reciverBankName;
    private String branchName;
    private  String acNO;
    private String ifsc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    public String getCoustomername() {
        return coustomername;
    }

    public void setCoustomername(String coustomername) {
        this.coustomername = coustomername;
    }

    public LocalDate getAggrementDate() {
        return aggrementDate;
    }

    public void setAggrementDate(LocalDate aggrementDate) {
        this.aggrementDate = aggrementDate;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getFacvoringName() {
        return facvoringName;
    }

    public void setFacvoringName(String facvoringName) {
        this.facvoringName = facvoringName;
    }

    public String getReciverBankName() {
        return reciverBankName;
    }

    public void setReciverBankName(String reciverBankName) {
        this.reciverBankName = reciverBankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAcNO() {
        return acNO;
    }

    public void setAcNO(String acNO) {
        this.acNO = acNO;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getTransactionAmountWords() {
        return transactionAmountWords;
    }

    public void setTransactionAmountWords(String transactionAmountWords) {
        this.transactionAmountWords = transactionAmountWords;
    }
}