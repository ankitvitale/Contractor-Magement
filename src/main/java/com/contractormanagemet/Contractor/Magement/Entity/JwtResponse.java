package com.contractormanagemet.Contractor.Magement.Entity;

public class JwtResponse {
    private  Admin admin;
    private  Superisor superisor;
    private String jwtToken;

    public JwtResponse(Admin admin, Superisor superisor, String jwtToken) {
        this.admin = admin;
        this.superisor = superisor;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
