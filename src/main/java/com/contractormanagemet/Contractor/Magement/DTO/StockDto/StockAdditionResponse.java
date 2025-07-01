package com.contractormanagemet.Contractor.Magement.DTO.StockDto;

import java.time.LocalDateTime;

public class StockAdditionResponse {
    private Long id;
    private double priceAdded;

    private int quantityAddedValue;

    private LocalDateTime addedAt;

    public StockAdditionResponse() {
    }

    public StockAdditionResponse(Long id, double priceAdded, int quantityAddedValue, LocalDateTime addedAt) {
        this.id = id;
        this.priceAdded = priceAdded;
        this.quantityAddedValue = quantityAddedValue;
        this.addedAt = addedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
