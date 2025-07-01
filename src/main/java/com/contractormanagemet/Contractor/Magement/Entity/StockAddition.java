package com.contractormanagemet.Contractor.Magement.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StockAddition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockAdditionId;

   private double priceAdded;

   private int quantityAddedValue;

   private LocalDateTime addedAt;


   @ManyToOne
   @JsonIgnoreProperties("stockAdditions") // prevent reverse nesting
   private Product product;


    public StockAddition() {
    }

    public StockAddition(Long stockAdditionId, double priceAdded, int quantityAddedValue, LocalDateTime addedAt, Product product) {
        this.stockAdditionId = stockAdditionId;
        this.priceAdded = priceAdded;
        this.quantityAddedValue = quantityAddedValue;
        this.addedAt = addedAt;
        this.product = product;
    }

    public Long getStockAdditionId() {
        return stockAdditionId;
    }

    public void setStockAdditionId(Long stockAdditionId) {
        this.stockAdditionId = stockAdditionId;
    }

    public double getPriceAdded() {
        return priceAdded;
    }

    public void setPriceAdded(double priceAdded) {
        this.priceAdded = priceAdded;
    }

    public int getQuantityAddedValue() {
        return quantityAddedValue;
    }

    public void setQuantityAddedValue(int quantityAddedValue) {
        this.quantityAddedValue = quantityAddedValue;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
