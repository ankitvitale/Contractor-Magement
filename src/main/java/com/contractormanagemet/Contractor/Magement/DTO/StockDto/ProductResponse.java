package com.contractormanagemet.Contractor.Magement.DTO.StockDto;

import com.contractormanagemet.Contractor.Magement.Entity.StockUsage;

import java.time.LocalDate;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private String totalQuantityString;
    private int totalQuantityValue;
    private LocalDate productAddOnDate;
    private List<StockUsageResponse> usages;

    private ProjectSummary project;
    private int usedQuantity;
    private int remainingQuantity;
    public ProductResponse() {
    }
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

    public int getTotalQuantityValue() {
        return totalQuantityValue;
    }

    public void setTotalQuantityValue(int totalQuantityValue) {
        this.totalQuantityValue = totalQuantityValue;
    }

    public LocalDate getProductAddOnDate() {
        return productAddOnDate;
    }

    public void setProductAddOnDate(LocalDate productAddOnDate) {
        this.productAddOnDate = productAddOnDate;
    }

    public List<StockUsageResponse> getUsages() {
        return usages;
    }

    public void setUsages(List<StockUsageResponse> usages) {
        this.usages = usages;
    }

    public ProjectSummary getProject() {
        return project;
    }

    public void setProject(ProjectSummary project) {
        this.project = project;
    }

    public int getUsedQuantity() {
        return usedQuantity;
    }

    public void setUsedQuantity(int usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public ProductResponse(Long id, String name, double price, String totalQuantityString, int totalQuantityValue, LocalDate productAddOnDate, List<StockUsageResponse> usages, ProjectSummary project, int usedQuantity, int remainingQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalQuantityString = totalQuantityString;
        this.totalQuantityValue = totalQuantityValue;
        this.productAddOnDate = productAddOnDate;
        this.usages = usages;
        this.project = project;
        this.usedQuantity = usedQuantity;
        this.remainingQuantity = remainingQuantity;
    }

    public static class ProjectSummary {
        private Long id;
        private String name;
        public ProjectSummary(){}
        public ProjectSummary(Long id, String name) {
            this.id = id;
            this.name = name;
        }

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
    }
}
