package com.contractormanagemet.Contractor.Magement.mapper;

import com.contractormanagemet.Contractor.Magement.DTO.ResidencyDto.ResidencyRequestDto;
import com.contractormanagemet.Contractor.Magement.Entity.Residency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResidencyMapper {

    // Map fields from DTO to Entity (except Project)
    @Mapping(target = "project", ignore = true)
    Residency toEntity(ResidencyRequestDto dto);



}
