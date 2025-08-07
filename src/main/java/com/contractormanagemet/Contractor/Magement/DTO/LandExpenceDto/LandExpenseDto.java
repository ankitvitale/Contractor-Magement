package com.contractormanagemet.Contractor.Magement.DTO.LandExpenceDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LandExpenseDto {

    private Long id;
    private String expenseName;
    private Double amount;
    private LocalDate date;
    private String updatedBy;
    private LocalDateTime updatedAt; // <-- Add this field
//
//    public LandExpenseDto(Long id, String expenseName, Double amount, LocalDate date, String updatedBy) {
//        this.id = id;
//        this.expenseName = expenseName;
//        this.amount = amount;
//        this.date = date;
//        this.updatedBy = updatedBy;
//    }

    public LandExpenseDto(Long id, String expenseName, Double amount, LocalDate date, String updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.expenseName = expenseName;
        this.amount = amount;
        this.date = date;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
