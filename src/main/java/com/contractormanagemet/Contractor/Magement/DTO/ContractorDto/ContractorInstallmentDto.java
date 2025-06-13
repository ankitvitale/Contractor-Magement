package com.contractormanagemet.Contractor.Magement.DTO.ContractorDto;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ExpensePayStatus;

import java.time.LocalDate;

public class ContractorInstallmentDto {


    private Long id;

    private LocalDate contractorPayDate;


    private Double  Amount;

    private String remark;

    private ExpensePayStatus contractorPayStatus;

    private Double RemainingAmount;

    public ContractorInstallmentDto(Long id, LocalDate contractorPayDate, Double amount, String remark, ExpensePayStatus contractorPayStatus, Double remainingAmount) {
        this.id = id;
        this.contractorPayDate = contractorPayDate;
        Amount = amount;
        this.remark = remark;
        this.contractorPayStatus = contractorPayStatus;
        RemainingAmount = remainingAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getContractorPayDate() {
        return contractorPayDate;
    }

    public void setContractorPayDate(LocalDate contractorPayDate) {
        this.contractorPayDate = contractorPayDate;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ExpensePayStatus getContractorPayStatus() {
        return contractorPayStatus;
    }

    public void setContractorPayStatus(ExpensePayStatus contractorPayStatus) {
        this.contractorPayStatus = contractorPayStatus;
    }

    public Double getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        RemainingAmount = remainingAmount;
    }
}
