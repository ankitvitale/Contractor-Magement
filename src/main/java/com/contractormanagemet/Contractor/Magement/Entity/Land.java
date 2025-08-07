package com.contractormanagemet.Contractor.Magement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Land.
 */
@Entity
@Table(name = "land")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Land implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "area")
    private Double area;

    @Column(name = "token_amount")
    private Double tokenAmount;

    @Column(name = "agreement_amount")
    private Double agreementAmount;

    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "landAddOn_date")
    private LocalDate landAddOnDate;
    private String updatedBy;

    private LocalDateTime updatedAt;
    @JsonIgnoreProperties(value = { "land" }, allowSetters = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Address address;

    @JsonIgnoreProperties(value = { "ownedLand", "purchasedLand" }, allowSetters = true)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(unique = true)
    private Person owner;

    @JsonIgnoreProperties(value = { "ownedLand", "purchasedLand" }, allowSetters = true)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(unique = true)
    private Person purchaser;

    @OneToMany( mappedBy = "land",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "land" }, allowSetters = true)
    private Set<Partner> partners = new HashSet<>();

    @JsonIgnoreProperties(value = { "land", "expenses", "residencies" }, allowSetters = true)
    @OneToOne( mappedBy = "land")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(Double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public Double getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(Double agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getLandAddOnDate() {
        return landAddOnDate;
    }

    public void setLandAddOnDate(LocalDate landAddOnDate) {
        this.landAddOnDate = landAddOnDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Person purchaser) {
        this.purchaser = purchaser;
    }

    public Set<Partner> getPartners() {
        return partners;
    }

    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}