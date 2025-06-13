package com.contractormanagemet.Contractor.Magement.DTO.ContractorDto;

import java.time.LocalDate;
import java.util.List;

public class ContractorResponseDto {
    private Long id;
    private String contractorName;
    private String sideName;
    private String type;
    private LocalDate addedOn;
    private Double total;
    private Double contractorPaidAmount;
    private Double reamingAmount;
    private Long projectId;
    private String projectName;
    private List<ContractorInstallmentDto> contractorInstallments;

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

    public List<ContractorInstallmentDto> getContractorInstallments() {
        return contractorInstallments;
    }

    public void setContractorInstallments(List<ContractorInstallmentDto> contractorInstallments) {
        this.contractorInstallments = contractorInstallments;
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

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
