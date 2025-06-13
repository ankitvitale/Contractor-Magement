package com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ProjectStatus;

public class ProjectResponseDto {

    private Long id;
    private String name;
    private ProjectStatus status;

    private String totalflat;

    private String buildingSize;
    private String Area;
    private String Facing;
    private LandResponseDto land;

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

    public LandResponseDto getLand() {
        return land;
    }

    public void setLand(LandResponseDto land) {
        this.land = land;
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
}