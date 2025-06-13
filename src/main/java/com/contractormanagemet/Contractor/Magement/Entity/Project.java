package com.contractormanagemet.Contractor.Magement.Entity;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "totalflat")
    private String totalflat;
    @Column(name = "buildingSize")
    private String buildingSize;

    @Column(name = "area")
    private String Area;
    @Column(name = "facing")
    private String Facing;

    @JsonIgnoreProperties(value = { "address", "owner", "purchaser", "partners", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private Land land;

    @OneToMany( mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "booking", "project" }, allowSetters = true)
    private Set<Residency> residencies = new HashSet<>();


    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project") // avoid circular loop on Product side
    private Set<Product> products = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "supervisor_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "supervisor_id")
    )
    @JsonBackReference(value = "supervisor-project")
    private Set<Superisor> supervisors = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("project") // avoid circular loop on Product side
    private List<StructureContractor> contractors = new ArrayList<>();

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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getTotalflat() {
        return totalflat;
    }

    public void setTotalflat(String totalflat) {
        this.totalflat = totalflat;
    }

    public String getBuildingSize() {
        return buildingSize;
    }

    public void setBuildingSize(String buildingSize) {
        this.buildingSize = buildingSize;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getFacing() {
        return Facing;
    }

    public void setFacing(String facing) {
        Facing = facing;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Superisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(Set<Superisor> supervisors) {
        this.supervisors = supervisors;
    }

    public List<StructureContractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<StructureContractor> contractors) {
        this.contractors = contractors;
    }
}