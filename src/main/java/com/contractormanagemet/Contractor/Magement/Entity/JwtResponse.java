package com.contractormanagemet.Contractor.Magement.Entity;

public class JwtResponse {
    private  Admin admin;
    private  Superisor superisor;
    private Employee employee;
    private SubAdmin subAdmin;
    private String jwtToken;

    public JwtResponse(Admin admin, Superisor superisor, Employee employee,SubAdmin subAdmin, String jwtToken) {
        this.admin = admin;
        this.superisor = superisor;
        this.employee = employee;
        this.subAdmin=subAdmin;
        this.jwtToken = jwtToken;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Superisor getSuperisor() {
        return superisor;
    }

    public void setSuperisor(Superisor superisor) {
        this.superisor = superisor;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SubAdmin getSubAdmin() {
        return subAdmin;
    }

    public void setSubAdmin(SubAdmin subAdmin) {
        this.subAdmin = subAdmin;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
