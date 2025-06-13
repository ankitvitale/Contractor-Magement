package com.contractormanagemet.Contractor.Magement.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contractor implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

        private String contractorName;
        private String sideName;
        private  String type;
        private LocalDate addedOn;
        private  Double total;
        private  Double contractorPaidAmount;
        private Double  reamingAmount;

       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name = "project_id")
      private Project project;


    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractorInstallment> contractorInstallments = new ArrayList<>();


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

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public List<ContractorInstallment> getContractorInstallments() {
        return contractorInstallments;
    }

    public void setContractorInstallments(List<ContractorInstallment> contractorInstallments) {
        this.contractorInstallments = contractorInstallments;
    }



}
