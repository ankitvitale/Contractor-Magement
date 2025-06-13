package com.contractormanagemet.Contractor.Magement.DTO.BookingDto;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.InstallmentStatus;

import java.time.LocalDate;

public class BookingInstallmentResponseDTO {
    private Long id;
    private LocalDate installmentDate;
    private Double installmentAmount;
    private String remark;
    private InstallmentStatus installmentStatus;
    private Long bookingId; // avoid returning full Booking entity

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public InstallmentStatus getInstallmentStatus() {
        return installmentStatus;
    }

    public void setInstallmentStatus(InstallmentStatus installmentStatus) {
        this.installmentStatus = installmentStatus;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
