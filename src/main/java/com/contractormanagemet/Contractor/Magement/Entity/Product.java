package com.contractormanagemet.Contractor.Magement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String totalQuantityString; // "100 kg"
    private int totalQuantityValue;     // 100

    private LocalDate productAddOnDate;
    private String updatedBy;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product") // prevent circular reference
    private List<StockUsage> usages = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("products")// avoid infinite loop when serializing Project
  //  @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product") // prevent circular reference
    private List<StockAddition> stockAdditions = new ArrayList<>();


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getTotalQuantityString() { return totalQuantityString; }
    public void setTotalQuantityString(String totalQuantityString) { this.totalQuantityString = totalQuantityString; }

    public int getTotalQuantityValue() { return totalQuantityValue; }
    public void setTotalQuantityValue(int totalQuantityValue) { this.totalQuantityValue = totalQuantityValue; }

    public LocalDate getProductAddOnDate() { return productAddOnDate; }
    public void setProductAddOnDate(LocalDate productAddOnDate) { this.productAddOnDate = productAddOnDate; }

    public List<StockUsage> getUsages() { return usages; }
    public void setUsages(List<StockUsage> usages) { this.usages = usages; }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getUsedQuantity() {
        return usages.stream().mapToInt(StockUsage::getQuantityUsed).sum();
    }

    public int getRemainingQuantity() {
        return totalQuantityValue - getUsedQuantity();
    }

    public List<StockAddition> getStockAdditions() {
        return stockAdditions;
    }

    public void setStockAdditions(List<StockAddition> stockAdditions) {
        this.stockAdditions = stockAdditions;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalQuantityString='" + totalQuantityString + '\'' +
                ", totalQuantityValue=" + totalQuantityValue +
                ", productAddOnDate=" + productAddOnDate +
                ", usages=" + usages +
                ", project=" + project +
                ", stockAdditions="+stockAdditions+
                '}';
    }
}
