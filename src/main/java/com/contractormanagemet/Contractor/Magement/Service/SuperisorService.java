package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto.AllowedSiteDto;
import com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto.SuperisorResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Superisor;
import com.contractormanagemet.Contractor.Magement.Repository.SuperisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperisorService {
    @Autowired
    private SuperisorRepository superisorRepository;
    public List<SuperisorResponseDto> findAllSuperisor() {
        return superisorRepository.findAll().stream().map(superisor -> {
            SuperisorResponseDto dto=new SuperisorResponseDto();
            dto.setId(superisor.getId());
            dto.setName(superisor.getName());
            dto.setEmail(superisor.getEmail());

            List<AllowedSiteDto> sites=superisor.getAllowedSite().stream().map(site->{
                AllowedSiteDto s=new AllowedSiteDto();
                s.setId(site.getId());
                s.setName(site.getName());
                s.setStatus(site.getStatus().toString());
                return s;
            }).collect(Collectors.toList());
            dto.setAllowedSite(sites);
            return dto;
        }).collect(Collectors.toList());
    }
}
