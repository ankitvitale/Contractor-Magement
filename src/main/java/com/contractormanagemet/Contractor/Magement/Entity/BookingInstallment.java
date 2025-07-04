package com.contractormanagemet.Contractor.Magement.Entity;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.InstallmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookingInstallment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "installment_Date")
    private LocalDate installmentDate;

    @Column(name = "installment_amount")
    private Double installmentAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "installment_status")
    private InstallmentStatus installmentStatus;

    @Column(name = "remark")
    private String remark;

    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    @JsonIgnoreProperties(value = { "bookingInstallments" }, allowSetters = true)
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(LocalDate installmentDate) {
        this.installmentDate = installmentDate;
    }

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public InstallmentStatus getInstallmentStatus() {
        return installmentStatus;
    }

    public void setInstallmentStatus(InstallmentStatus installmentStatus) {
        this.installmentStatus = installmentStatus;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
