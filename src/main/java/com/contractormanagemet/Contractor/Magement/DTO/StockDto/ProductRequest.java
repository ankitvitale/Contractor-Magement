package com.contractormanagemet.Contractor.Magement.DTO.StockDto;

import java.time.LocalDate;

public class ProductRequest {
    private String name;
    private double price;
    private String totalQuantityString;
    private LocalDate productAddOnDate;
    private Long projectId;  // use projectId directly instead of nested object

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTotalQuantityString() {
        return totalQuantityString;
    }

    public void setTotalQuantityString(String totalQuantityString) {
        this.totalQuantityString = totalQuantityString;
    }

    public LocalDate getProductAddOnDate() {
        return productAddOnDate;
    }

    public void setProductAddOnDate(LocalDate productAddOnDate) {
        this.productAddOnDate = productAddOnDate;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
// getters and setters


}
