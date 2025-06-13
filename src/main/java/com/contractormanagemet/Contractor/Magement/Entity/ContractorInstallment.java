package com.contractormanagemet.Contractor.Magement.Entity;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ExpensePayStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ContractorInstallment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "payment_Date")
    private LocalDate contractorPayDate;

    @Column(name = "contractor_amount")
    private Double  Amount;

    @Column(name = "remark")
    private String remark;

    @Enumerated(EnumType.STRING)
    @Column(name = "contractor_status")
    private ExpensePayStatus contractorPayStatus;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    @JsonBackReference // Use this on the child side
    private Contractor contractor;


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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
}
