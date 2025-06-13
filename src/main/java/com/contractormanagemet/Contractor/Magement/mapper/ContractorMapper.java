package com.contractormanagemet.Contractor.Magement.mapper;

import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.AllContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorInstallmentDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorRequestDto;
import com.contractormanagemet.Contractor.Magement.Entity.Contractor;
import com.contractormanagemet.Contractor.Magement.Entity.ContractorInstallment;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reamingAmount", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "contractorInstallments", ignore = true)
    Contractor toEntity(ContractorRequestDto dto);

    List<AllContractorResponseDto> toAllContractorResponseDtoList(List<Contractor> contractors);

   List<ContractorInstallmentDto> toInstallmentDtoList(List<ContractorInstallment> installments);


    @Mapping(target = "contractorInstallments", source = "contractorInstallments")
    @Mapping(source = "reamingAmount", target = "reamingAmount")
    AllContractorResponseDto toDto(Contractor contractor);


    ContractorInstallmentDto toInstallmentDto(ContractorInstallment installment);




}
