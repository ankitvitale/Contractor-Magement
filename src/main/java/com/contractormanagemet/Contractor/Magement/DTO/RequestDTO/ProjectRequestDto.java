package com.contractormanagemet.Contractor.Magement.DTO.RequestDTO;

import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ProjectStatus;

public class ProjectRequestDto {
    private Long id;
    private String name;
    private String totalflat;
    private String BuildingSize;
    private String Area;
    private String Facing;
    private ProjectStatus status;
    private Long landId;

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

    public String getTotalflat() {
        return totalflat;
    }

    public void setTotalflat(String totalflat) {
        this.totalflat = totalflat;
    }

    public String getBuildingSize() {
        return BuildingSize;
    }

    public void setBuildingSize(String buildingSize) {
        BuildingSize = buildingSize;
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Long getLandId() {
        return landId;
    }

    public void setLandId(Long landId) {
        this.landId = landId;
    }
}
