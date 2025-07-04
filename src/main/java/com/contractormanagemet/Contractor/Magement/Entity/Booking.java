package com.contractormanagemet.Contractor.Magement.Entity;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deal_price")
    private Double dealPrice;

    @Column(name = "token_amount")
    private Double tokenAmount;

    @Column(name = "agreement_amount")
    private Double agreementAmount;

    @Column(name = "stamp_duty_amount")
    private Double stampDutyAmount;

    @Column(name = "registration_amount")
    private Double registrationAmount;

    @Column(name = "gst_amount")
    private Double gstAmount;

    @Column(name = "electric_water_ammount")
    private Double electricWaterAmmount;

    @Column(name = "legal_charges_ammout")
    private Double legalChargesAmmout;

    @Column(name = "booked_on")
    private LocalDate bookedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    private String updatedBy;

//

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "booking" }, allowSetters = true)
    private List<BookingInstallment> bookingInstallments = new ArrayList<>();

    @JsonIgnoreProperties(value = { "booking" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Customer customer;

    @JsonIgnoreProperties(value = { "booking", "project" }, allowSetters = true)
    @OneToOne( mappedBy = "booking")
    private Residency residency;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Booking id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDealPrice() {
        return this.dealPrice;
    }

    public Booking dealPrice(Double dealPrice) {
        this.setDealPrice(dealPrice);
        return this;
    }

    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public Double getTokenAmount() {
        return this.tokenAmount;
    }

    public Booking tokenAmount(Double tokenAmount) {
        this.setTokenAmount(tokenAmount);
        return this;
    }

    public void setTokenAmount(Double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public Double getAgreementAmount() {
        return this.agreementAmount;
    }

    public Booking agreementAmount(Double agreementAmount) {
        this.setAgreementAmount(agreementAmount);
        return this;
    }

    public void setAgreementAmount(Double agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public Double getStampDutyAmount() {
        return this.stampDutyAmount;
    }

    public Booking stampDutyAmount(Double stampDutyAmount) {
        this.setStampDutyAmount(stampDutyAmount);
        return this;
    }

    public void setStampDutyAmount(Double stampDutyAmount) {
        this.stampDutyAmount = stampDutyAmount;
    }

    public Double getRegistrationAmount() {
        return this.registrationAmount;
    }

    public Booking registrationAmount(Double registrationAmount) {
        this.setRegistrationAmount(registrationAmount);
        return this;
    }

    public void setRegistrationAmount(Double registrationAmount) {
        this.registrationAmount = registrationAmount;
    }

    public Double getGstAmount() {
        return this.gstAmount;
    }

    public Booking gstAmount(Double gstAmount) {
        this.setGstAmount(gstAmount);
        return this;
    }

    public void setGstAmount(Double gstAmount) {
        this.gstAmount = gstAmount;
    }

    public Double getElectricWaterAmmount() {
        return this.electricWaterAmmount;
    }

    public Booking electricWaterAmmount(Double electricWaterAmmount) {
        this.setElectricWaterAmmount(electricWaterAmmount);
        return this;
    }

    public void setElectricWaterAmmount(Double electricWaterAmmount) {
        this.electricWaterAmmount = electricWaterAmmount;
    }

    public Double getLegalChargesAmmout() {
        return this.legalChargesAmmout;
    }

    public Booking legalChargesAmmout(Double legalChargesAmmout) {
        this.setLegalChargesAmmout(legalChargesAmmout);
        return this;
    }

    public void setLegalChargesAmmout(Double legalChargesAmmout) {
        this.legalChargesAmmout = legalChargesAmmout;
    }

    public LocalDate getBookedOn() {
        return this.bookedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Booking bookedOn(LocalDate bookedOn) {
        this.setBookedOn(bookedOn);
        return this;
    }

    public void setBookedOn(LocalDate bookedOn) {
        this.bookedOn = bookedOn;
    }

    public BookingStatus getBookingStatus() {
        return this.bookingStatus;
    }

    public Booking bookingStatus(BookingStatus bookingStatus) {
        this.setBookingStatus(bookingStatus);
        return this;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<BookingInstallment> getBookingInstallments() {
        return bookingInstallments;
    }

    public void setBookingInstallments(List<BookingInstallment> bookingInstallments) {
        this.bookingInstallments = bookingInstallments;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Booking customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Residency getResidency() {
        return this.residency;
    }

    public void setResidency(Residency residency) {
        if (this.residency != null) {
            this.residency.setBooking(null);
        }
        if (residency != null) {
            residency.setBooking(this);
        }
        this.residency = residency;
    }

    public Booking residency(Residency residency) {
        this.setResidency(residency);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }
        return getId() != null && getId().equals(((Booking) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", dealPrice=" + getDealPrice() +
            ", tokenAmount=" + getTokenAmount() +
            ", agreementAmount=" + getAgreementAmount() +
            ", stampDutyAmount=" + getStampDutyAmount() +
            ", registrationAmount=" + getRegistrationAmount() +
            ", gstAmount=" + getGstAmount() +
            ", electricWaterAmmount=" + getElectricWaterAmmount() +
            ", legalChargesAmmout=" + getLegalChargesAmmout() +
            ", bookedOn='" + getBookedOn() + "'" +
            ", bookingStatus='" + getBookingStatus() + "'" +
            "}";
    }
}