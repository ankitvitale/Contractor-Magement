package com.contractormanagemet.Contractor.Magement.DTO.BookingDto;

import com.contractormanagemet.Contractor.Magement.Entity.Booking;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.BookingStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingPaymentDto {
    private Long id;


//    private Double dealPrice;
//
//
//    private Double tokenAmount;
//
//
//    private Double agreementAmount;
//
//
//    private Double stampDutyAmount;
//
//
//    private Double registrationAmount;
//
//
//    private Double gstAmount;
//
//
//    private Double electricWaterAmmount;
//
//
//    private Double legalChargesAmmout;
//
//
//    private LocalDate bookedOn;
//
//
//    private BookingStatus bookingStatus;

    private List<BookingInstallmentDTO> bookingInstallments = new ArrayList<>();
    public BookingPaymentDto(Booking booking) {
        this.id = booking.getId();
        this.bookingInstallments = booking.getBookingInstallments()
                .stream()
                .map(BookingInstallmentDTO::new)
                .collect(Collectors.toList());
    }

//    public BookingPaymentDto(Long id, Double dealPrice, Double tokenAmount, Double agreementAmount, Double stampDutyAmount, Double registrationAmount, Double gstAmount, Double electricWaterAmmount, Double legalChargesAmmout, LocalDate bookedOn, BookingStatus bookingStatus, List<BookingInstallmentDTO> bookingInstallments) {
//        this.id = id;
//        this.dealPrice = dealPrice;
//        this.tokenAmount = tokenAmount;
//        this.agreementAmount = agreementAmount;
//        this.stampDutyAmount = stampDutyAmount;
//        this.registrationAmount = registrationAmount;
//        this.gstAmount = gstAmount;
//        this.electricWaterAmmount = electricWaterAmmount;
//        this.legalChargesAmmout = legalChargesAmmout;
//        this.bookedOn = bookedOn;
//        this.bookingStatus = bookingStatus;
//        this.bookingInstallments = bookingInstallments;
//    }
//
//    public BookingPaymentDto(Booking booking) {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Double getDealPrice() {
//        return dealPrice;
//    }
//
//    public void setDealPrice(Double dealPrice) {
//        this.dealPrice = dealPrice;
//    }
//
//    public Double getTokenAmount() {
//        return tokenAmount;
//    }
//
//    public void setTokenAmount(Double tokenAmount) {
//        this.tokenAmount = tokenAmount;
//    }
//
//    public Double getAgreementAmount() {
//        return agreementAmount;
//    }
//
//    public void setAgreementAmount(Double agreementAmount) {
//        this.agreementAmount = agreementAmount;
//    }
//
//    public Double getStampDutyAmount() {
//        return stampDutyAmount;
//    }
//
//    public void setStampDutyAmount(Double stampDutyAmount) {
//        this.stampDutyAmount = stampDutyAmount;
//    }
//
//    public Double getRegistrationAmount() {
//        return registrationAmount;
//    }
//
//    public void setRegistrationAmount(Double registrationAmount) {
//        this.registrationAmount = registrationAmount;
//    }
//
//    public Double getGstAmount() {
//        return gstAmount;
//    }
//
//    public void setGstAmount(Double gstAmount) {
//        this.gstAmount = gstAmount;
//    }
//
//    public Double getElectricWaterAmmount() {
//        return electricWaterAmmount;
//    }
//
//    public void setElectricWaterAmmount(Double electricWaterAmmount) {
//        this.electricWaterAmmount = electricWaterAmmount;
//    }
//
//    public Double getLegalChargesAmmout() {
//        return legalChargesAmmout;
//    }
//
//    public void setLegalChargesAmmout(Double legalChargesAmmout) {
//        this.legalChargesAmmout = legalChargesAmmout;
//    }
//
//    public LocalDate getBookedOn() {
//        return bookedOn;
//    }
//
//    public void setBookedOn(LocalDate bookedOn) {
//        this.bookedOn = bookedOn;
//    }
//
//    public BookingStatus getBookingStatus() {
//        return bookingStatus;
//    }
//
//    public void setBookingStatus(BookingStatus bookingStatus) {
//        this.bookingStatus = bookingStatus;
//    }

    public List<BookingInstallmentDTO> getBookingInstallments() {
        return bookingInstallments;
    }

    public void setBookingInstallments(List<BookingInstallmentDTO> bookingInstallments) {
        this.bookingInstallments = bookingInstallments;
    }
}
