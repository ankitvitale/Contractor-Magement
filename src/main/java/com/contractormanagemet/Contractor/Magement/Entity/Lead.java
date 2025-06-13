package com.contractormanagemet.Contractor.Magement.Entity;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.LeadStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Lead.
 */
@Entity
@Table(name = "jhi_lead")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Lead implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "found_on")
    private LocalDate foundOn;
    @Column(name = "remark")
    private String remark;

    @Column(name = "remark_date")
    private LocalDate remarkdate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LeadStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lead")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lead" }, allowSetters = true)
    private List<LeadLog> leadLogs = new ArrayList<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Lead(){}



    public Lead(Long id, String name, String jobTitle, String companyName, String email, String phoneNumber, LocalDate foundOn, String remark, LocalDate remarkdate, LeadStatus status, List<LeadLog> leadLogs) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.foundOn = foundOn;
        this.remark = remark;
        this.remarkdate = remarkdate;
        this.status = status;
        this.leadLogs = leadLogs;
    }

    public Long getId() {
        return this.id;
    }

    public Lead id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Lead name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public Lead jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }


    

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Lead companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return this.email;
    }

    public Lead email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Lead phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getFoundOn() {
        return this.foundOn;
    }

    public Lead foundOn(LocalDate foundOn) {
        this.setFoundOn(foundOn);
        return this;
    }

    public void setFoundOn(LocalDate foundOn) {
        this.foundOn = foundOn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getRemarkdate() {
        return remarkdate;
    }

    public void setRemarkdate(LocalDate remarkdate) {
        this.remarkdate = remarkdate;
    }

    public LeadStatus getStatus() {
        return this.status;
    }

    public Lead status(LeadStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(LeadStatus status) {
        this.status = status;
    }

    public List<LeadLog> getLeadLogs() {
        return this.leadLogs;
    }

    public void setLeadLogs(List<LeadLog> leadLogs) {
        if (this.leadLogs != null) {
            this.leadLogs.forEach(i -> i.setLead(null));
        }
        if (leadLogs != null) {
            leadLogs.forEach(i -> i.setLead(this));
        }
        this.leadLogs = leadLogs;
    }

    public Lead leadLogs(List<LeadLog> leadLogs) {
        this.setLeadLogs(leadLogs);
        return this;
    }

    public Lead addLeadLog(LeadLog leadLog) {
        this.leadLogs.add(leadLog);
        leadLog.setLead(this);
        return this;
    }

    public Lead removeLeadLog(LeadLog leadLog) {
        this.leadLogs.remove(leadLog);
        leadLog.setLead(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lead)) {
            return false;
        }
        return getId() != null && getId().equals(((Lead) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lead{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", jobTitle='" + getJobTitle() + "'" +
                ", companyName='" + getCompanyName() + "'" +
                ", email='" + getEmail() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                ", foundOn='" + getFoundOn() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}
