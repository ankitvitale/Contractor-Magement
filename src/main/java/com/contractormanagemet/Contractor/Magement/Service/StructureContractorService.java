package com.contractormanagemet.Contractor.Magement.Service;


import com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto.ContractorListWithTotalDto;
import com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto.StructureContractorRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto.StructureContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Employee;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import com.contractormanagemet.Contractor.Magement.Entity.StructureContractor;
import com.contractormanagemet.Contractor.Magement.Entity.SubAdmin;
import com.contractormanagemet.Contractor.Magement.Repository.EmployeeRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ProjectRepository;
import com.contractormanagemet.Contractor.Magement.Repository.StructureContractorRepository;
import com.contractormanagemet.Contractor.Magement.Repository.SubAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StructureContractorService {


    @Autowired
    private StructureContractorRepository contractorRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private SubAdminRepository subAdminRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public StructureContractorResponseDto createContractor(StructureContractorRequestDto dto) {
        Project project = projectRepo.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        StructureContractor contractor = new StructureContractor();
        contractor.setPayableName(dto.getPayableName());
        contractor.setRemark(dto.getRemark());
        contractor.setDate(dto.getDate());
        contractor.setAmount(dto.getAmount());
        contractor.setProject(project);

        StructureContractor saved = contractorRepo.save(contractor);

        return toDto(saved);
    }

    public List<StructureContractorResponseDto> getAllContractors() {
        return contractorRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private StructureContractorResponseDto toDto(StructureContractor contractor) {
        StructureContractorResponseDto dto = new StructureContractorResponseDto();
        dto.setId(contractor.getId());
        dto.setPayableName(contractor.getPayableName());
        dto.setRemark(contractor.getRemark());
        dto.setDate(contractor.getDate());
        dto.setAmount(contractor.getAmount());
        dto.setProjectId(contractor.getProject().getId());
        dto.setProjectName(contractor.getProject().getName());
        dto.setUpdatedBy(contractor.getUpdatedBy());
        dto.setUpdatedAt(contractor.getUpdatedAt());
        return dto;
    }

    public ContractorListWithTotalDto getContractorsByProjectId(Long projectId) {
        List<StructureContractor> contractors = contractorRepo.findByProjectId(projectId);

        List<StructureContractorResponseDto> dtoList = contractors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        double totalAmount = contractors.stream()
                .mapToDouble(StructureContractor::getAmount)
                .sum();

        ContractorListWithTotalDto result = new ContractorListWithTotalDto();
        result.setContractors(dtoList);
        result.setTotalAmount(totalAmount);
        return result;
    }




    public StructureContractorResponseDto updateContractor(Long id, StructureContractorRequestDto dto) {
        StructureContractor contractor = contractorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contractor not found"));

        Project project = projectRepo.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Update contractor fields
        contractor.setPayableName(dto.getPayableName());
        contractor.setRemark(dto.getRemark());
        contractor.setDate(dto.getDate());
        contractor.setAmount(dto.getAmount());
        contractor.setProject(project);

        // 🔐 Get current user info
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        Employee employee = employeeRepository.findByEmail(email);
        SubAdmin subAdmin = subAdminRepository.findByEmail(email);

        String fullNameWithEmail;

        if (employee != null) {
            fullNameWithEmail = employee.getName() + " (" + employee.getEmail() + ")";
        } else if (subAdmin != null) {
            fullNameWithEmail = subAdmin.getName() + " (" + subAdmin.getEmail() + ")";
        } else if (isAdmin) {
            fullNameWithEmail = "Admin (" + email + ")";
        } else {
            fullNameWithEmail = "Edit By Admin  (" + email + ")";
        }

        // Set updatedBy
        contractor.setUpdatedBy(fullNameWithEmail);
        contractor.setUpdatedAt(LocalDateTime.now());

        // Save and return DTO
        StructureContractor updated = contractorRepo.save(contractor);
        return toDto(updated);
    }


    public void deleteContractor(Long id) {
        if (!contractorRepo.existsById(id)) {
            throw new RuntimeException("Contractor not found");
        }
        contractorRepo.deleteById(id);
    }

    public StructureContractorResponseDto getContractorById(Long id) {
        StructureContractor contractor = contractorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contractor not found with id: " + id));
        return toDto(contractor);
    }

}
