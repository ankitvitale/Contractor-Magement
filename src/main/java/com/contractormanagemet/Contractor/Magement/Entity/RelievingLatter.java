package com.contractormanagemet.Contractor.Magement.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RelievingLatter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long empId;
    private String employeeName;
    @Column(name = "creation_date")
    private LocalDate currentDate;
    private LocalDate resignationDate;

    private LocalDate dateOfjoing;
    private LocalDate lastworkingdate;
    private String designation;
    private String department;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public LocalDate getLastworkingdate() {
        return lastworkingdate;
    }

    public void setLastworkingdate(LocalDate lastworkingdate) {
        this.lastworkingdate = lastworkingdate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public LocalDate getDateOfjoing() {
        return dateOfjoing;
    }

    public void setDateOfjoing(LocalDate dateOfjoing) {
        this.dateOfjoing = dateOfjoing;
    }
}