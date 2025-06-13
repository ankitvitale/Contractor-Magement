package com.contractormanagemet.Contractor.Magement.DTO.BookingDto;

import com.contractormanagemet.Contractor.Magement.Entity.BookingInstallment;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.InstallmentStatus;

import java.time.LocalDate;

public class BookingInstallmentDTO {
    private Long id;
    private LocalDate installmentDate;
    private Double installmentAmount;
    private String remark;
    private InstallmentStatus installmentStatus;

    public BookingInstallmentDTO(){}
    public BookingInstallmentDTO(Long id, LocalDate installmentDate, Double installmentAmount, String remark, InstallmentStatus installmentStatus) {
        this.id = id;
        this.installmentDate = installmentDate;
        this.installmentAmount = installmentAmount;
        this.remark = remark;
        this.installmentStatus = installmentStatus;
    }

    public BookingInstallmentDTO(BookingInstallment bookingInstallment) {
        this.id = bookingInstallment.getId();
        this.installmentDate = bookingInstallment.getInstallmentDate();
        this.installmentAmount = bookingInstallment.getInstallmentAmount();
        this.installmentStatus = bookingInstallment.getInstallmentStatus();
        this.remark = bookingInstallment.getRemark();
    }

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
}
