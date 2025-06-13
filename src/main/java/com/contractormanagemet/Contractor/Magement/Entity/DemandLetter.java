package com.contractormanagemet.Contractor.Magement.Entity;

import jakarta.persistence.*;

@Entity
public class DemandLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String faltno;
    private String amount;
    private String sitename;
    private String favorOf;
    private String bankName;
    private String branch;
    private String acNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaltno() {
        return faltno;
    }

    public void setFaltno(String faltno) {
        this.faltno = faltno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getFavorOf() {
        return favorOf;
    }

    public void setFavorOf(String favorOf) {
        this.favorOf = favorOf;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }
}