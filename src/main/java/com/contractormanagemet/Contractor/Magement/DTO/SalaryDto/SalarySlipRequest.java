package com.contractormanagemet.Contractor.Magement.DTO.SalaryDto;

import java.time.LocalDate;

public class SalarySlipRequest {
    private Long employeeId;
    private String employeeName;
    private String designation;
    private String department;
    private String month;
    private int year;
    private int paidDays;
    private String uanNo;
    private String bankAccountNo;
    private LocalDate dateOfJoining;

    private double basic;
    private double hra;
    private double bonus;
    private double allowance;

    private double pfAmount;
    private double professionalTax;
    private double otherDeductions;
    private double loan;

    // getters and setters


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPaidDays() {
        return paidDays;
    }

    public void setPaidDays(int paidDays) {
        this.paidDays = paidDays;
    }

    public String getUanNo() {
        return uanNo;
    }

    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getPfAmount() {
        return pfAmount;
    }

    public void setPfAmount(double pfAmount) {
        this.pfAmount = pfAmount;
    }

    public double getProfessionalTax() {
        return professionalTax;
    }

    public void setProfessionalTax(double professionalTax) {
        this.professionalTax = professionalTax;
    }

    public double getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }
}
