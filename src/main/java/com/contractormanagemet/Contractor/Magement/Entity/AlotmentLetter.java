package com.contractormanagemet.Contractor.Magement.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AlotmentLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String apartmentName;
    private String khno;
    private String mouzeNo;
    private String sheetNo;
    private String citySurveyNo;
    private String name;
    private String totalamount;
    private String totalamountword;
    private LocalDate agreementDate;
    private Double sqmtrs;
    private Double sqft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getMouzeNo() {
        return mouzeNo;
    }

    public void setMouzeNo(String mouzeNo) {
        this.mouzeNo = mouzeNo;
    }

    public String getSheetNo() {
        return sheetNo;
    }

    public void setSheetNo(String sheetNo) {
        this.sheetNo = sheetNo;
    }

    public String getCitySurveyNo() {
        return citySurveyNo;
    }

    public void setCitySurveyNo(String citySurveyNo) {
        this.citySurveyNo = citySurveyNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getTotalamountword() {
        return totalamountword;
    }

    public void setTotalamountword(String totalamountword) {
        this.totalamountword = totalamountword;
    }

    public LocalDate getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(LocalDate agreementDate) {
        this.agreementDate = agreementDate;
    }

    public Double getSqmtrs() {
        return sqmtrs;
    }

    public void setSqmtrs(Double sqmtrs) {
        this.sqmtrs = sqmtrs;
    }

    public Double getSqft() {
        return sqft;
    }

    public void setSqft(Double sqft) {
        this.sqft = sqft;
    }
}