package com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto;

import java.util.List;

public class SuperisorResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<AllowedSiteDto> allowedSite;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AllowedSiteDto> getAllowedSite() {
        return allowedSite;
    }

    public void setAllowedSite(List<AllowedSiteDto> allowedSite) {
        this.allowedSite = allowedSite;
    }
}
