package com.contractormanagemet.Contractor.Magement.Entity;



import com.contractormanagemet.Contractor.Magement.Entity.enumeration.AvailabilityStatus;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.FlatType;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ResidencyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Residency.
 */
@Entity
@Table(name = "residency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Residency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "residency_type")
    private ResidencyType residencyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "flat_type")
    private FlatType flatType;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status")
    private AvailabilityStatus availabilityStatus;

    @Column(name = "floor_number")
    private String floorNumber;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "price")
    private Double price;

    @JsonIgnoreProperties(value = { "customer", "residency" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Booking booking;

    @ManyToOne
    @JsonIgnoreProperties(value = { "land", "expenses", "residencies" }, allowSetters = true)
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Residency id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Residency name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResidencyType getResidencyType() {
        return this.residencyType;
    }

    public Residency residencyType(ResidencyType residencyType) {
        this.setResidencyType(residencyType);
        return this;
    }

    public void setResidencyType(ResidencyType residencyType) {
        this.residencyType = residencyType;
    }

    public FlatType getFlatType() {
        return this.flatType;
    }

    public Residency flatType(FlatType flatType) {
        this.setFlatType(flatType);
        return this;
    }

    public void setFlatType(FlatType flatType) {
        this.flatType = flatType;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return this.availabilityStatus;
    }

    public Residency availabilityStatus(AvailabilityStatus availabilityStatus) {
        this.setAvailabilityStatus(availabilityStatus);
        return this;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getFloorNumber() {
        return this.floorNumber;
    }

    public Residency floorNumber(String floorNumber) {
        this.setFloorNumber(floorNumber);
        return this;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Residency identifier(String identifier) {
        this.setIdentifier(identifier);
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Double getPrice() {
        return this.price;
    }

    public Residency price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Residency booking(Booking booking) {
        this.setBooking(booking);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Residency project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Residency)) {
            return false;
        }
        return getId() != null && getId().equals(((Residency) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Residency{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", residencyType='" + getResidencyType() + "'" +
                ", flatType='" + getFlatType() + "'" +
                ", availabilityStatus='" + getAvailabilityStatus() + "'" +
                ", floorNumber='" + getFloorNumber() + "'" +
                ", identifier='" + getIdentifier() + "'" +
                ", price=" + getPrice() +
                "}";
    }


}

