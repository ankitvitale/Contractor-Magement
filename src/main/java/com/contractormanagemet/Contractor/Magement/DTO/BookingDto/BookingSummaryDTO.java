package com.contractormanagemet.Contractor.Magement.DTO.BookingDto;

import java.util.List;

public class BookingSummaryDTO {

    private String customerName;
    private String residencyName;
    private String identifier;
    private Double dealPrice;
    private Double tokenAmount; // New field
    private Double agreementAmount; // New field
    private Double remainingAmount; // New field for remaining amount
    private List<BookingInstallmentDTO> bookingInstallments;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getResidencyName() {
        return residencyName;
    }

    public void setResidencyName(String residencyName) {
        this.residencyName = residencyName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(Double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public Double getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(Double agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public List<BookingInstallmentDTO> getBookingInstallments() {
        return bookingInstallments;
    }

    public void setBookingInstallments(List<BookingInstallmentDTO> bookingInstallments) {
        this.bookingInstallments = bookingInstallments;
    }
}
