package com.contractormanagemet.Contractor.Magement.mapper;

import com.contractormanagemet.Contractor.Magement.DTO.PartnerDto;
import com.contractormanagemet.Contractor.Magement.Entity.Partner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PartnerMapper {

    public abstract PartnerDto toPartnerDto(Partner partner);
}
