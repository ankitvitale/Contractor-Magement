package com.contractormanagemet.Contractor.Magement.mapper;


import com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO.AddressResponseDTO;
import com.contractormanagemet.Contractor.Magement.Entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class  AddressMapper {

    public abstract AddressResponseDTO addressResponseDTO(Address address);
}
