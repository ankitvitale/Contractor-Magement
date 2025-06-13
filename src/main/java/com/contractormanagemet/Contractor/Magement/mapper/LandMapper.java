package com.contractormanagemet.Contractor.Magement.mapper;

import com.contractormanagemet.Contractor.Magement.DTO.RequestDTO.LandRequestDTO;
import com.contractormanagemet.Contractor.Magement.Entity.Land;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" ,uses = {PartnerMapper.class, AddressMapper.class})
public abstract  class LandMapper {
    @Mapping(source = "partners", target = "partners")
    @Mapping(target = "soldAmount", expression = "java(land.getTotalAmount() - (land.getAgreementAmount() + land.getTokenAmount()))")
    public abstract LandRequestDTO toLandRequestDTO(Land land);
}
