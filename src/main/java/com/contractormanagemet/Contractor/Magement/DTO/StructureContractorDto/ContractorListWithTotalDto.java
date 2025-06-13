package com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto;

import java.util.List;

public class ContractorListWithTotalDto {
    private List<StructureContractorResponseDto> contractors;
    private double totalAmount;

    // Getters and setters


    public List<StructureContractorResponseDto> getContractors() {
        return contractors;
    }

    public void setContractors(List<StructureContractorResponseDto> contractors) {
        this.contractors = contractors;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
