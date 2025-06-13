package com.contractormanagemet.Contractor.Magement.DTO.ContractorDto;

import com.contractormanagemet.Contractor.Magement.Entity.Contractor;
import com.contractormanagemet.Contractor.Magement.Entity.ContractorInstallment;

import java.time.LocalDate;
import java.util.List;

public class AllContractorResponseDto {
    private Long id;
    private String contractorName;
    private String sideName;
    private String type;
    private String addedOn;
    private Double total;
    private Double contractorPaidAmount;
    private Double reamingAmount;
    private List<ContractorInstallment> contractorInstallments;

    public AllContractorResponseDto(Long id, String contractorName, String sideName, String type, LocalDate addedOn, Double total, Double contractorPaidAmount, Double reamingAmount, List<ContractorInstallment> contractorInstallments) {
        this.id = id;
        this.contractorName = contractorName;
        this.sideName = sideName;
        this.type = type;
        this.addedOn = String.valueOf(addedOn);
        this.total = total;
        this.contractorPaidAmount = contractorPaidAmount;
        this.reamingAmount = reamingAmount;
        this.contractorInstallments = contractorInstallments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getSideName() {
        return sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getContractorPaidAmount() {
        return contractorPaidAmount;
    }

    public void setContractorPaidAmount(Double contractorPaidAmount) {
        this.contractorPaidAmount = contractorPaidAmount;
    }

    public Double getReamingAmount() {
        return reamingAmount;
    }

    public void setReamingAmount(Double reamingAmount) {
        this.reamingAmount = reamingAmount;
    }

    public List<ContractorInstallment> getContractorInstallments() {
        return contractorInstallments;
    }

    public void setContractorInstallments(List<ContractorInstallment> contractorInstallments) {
        this.contractorInstallments = contractorInstallments;
    }
    public static AllContractorResponseDto fromEntity(Contractor contractor) {
        return new AllContractorResponseDto(
                contractor.getId(),
                contractor.getContractorName(),
                contractor.getSideName(),
                contractor.getType(),
                contractor.getAddedOn(),
                contractor.getTotal(),
                contractor.getContractorPaidAmount(),
                contractor.getReamingAmount(),
                contractor.getContractorInstallments()
        );
    }

}
