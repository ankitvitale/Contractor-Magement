package com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto;

import java.time.LocalDate;

public class StructureContractorResponseDto {
    private Long id;
    private String payableName;
    private String remark;
    private LocalDate date;
    private Double amount;
    private Long projectId;
    private String projectName;
    private String updatedBy;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayableName() {
        return payableName;
    }

    public void setPayableName(String payableName) {
        this.payableName = payableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
