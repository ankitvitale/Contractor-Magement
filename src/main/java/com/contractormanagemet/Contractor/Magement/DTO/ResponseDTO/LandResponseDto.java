package com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO;

import com.contractormanagemet.Contractor.Magement.DTO.PartnerDto;

import java.util.Set;

public class LandResponseDto {
    private Long id;
    private Double area;
    private Double tokenAmount;
    private Double agreementAmount;
    private Double totalAmount;
    private Double soldAmount;
    private AddressResponseDTO address;
    private Set<PartnerDto> partners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(Double tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public Double getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(Double agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(Double soldAmount) {
        this.soldAmount = soldAmount;
    }

    public AddressResponseDTO getAddress() {
        return address;
    }

    public void setAddress(AddressResponseDTO address) {
        this.address = address;
    }

    public Set<PartnerDto> getPartners() {
        return partners;
    }

    public void setPartners(Set<PartnerDto> partners) {
        this.partners = partners;
    }
}
