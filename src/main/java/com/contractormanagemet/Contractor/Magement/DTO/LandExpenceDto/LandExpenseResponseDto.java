package com.contractormanagemet.Contractor.Magement.DTO.LandExpenceDto;

import java.util.List;

public class LandExpenseResponseDto {
    private List<LandExpenseDto> expenses;
    private Double total;

    public LandExpenseResponseDto(List<LandExpenseDto> expenses, Double total) {
        this.expenses = expenses;
        this.total = total;
    }

    // Getters and Setters


    public List<LandExpenseDto> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<LandExpenseDto> expenses) {
        this.expenses = expenses;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
