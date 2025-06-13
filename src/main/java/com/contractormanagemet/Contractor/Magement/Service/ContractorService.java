package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.AllContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorInstallmentDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Contractor;
import com.contractormanagemet.Contractor.Magement.Entity.ContractorInstallment;
import com.contractormanagemet.Contractor.Magement.Entity.Project;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.ExpensePayStatus;
import com.contractormanagemet.Contractor.Magement.Exception.ResourceNotFoundException;
import com.contractormanagemet.Contractor.Magement.Repository.ContractorInstallmentRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ContractorRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ProjectRepository;
import com.contractormanagemet.Contractor.Magement.mapper.ContractorMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractorService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ContractorMapper contractorMapper;
    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private ContractorInstallmentRepository contractorInstallmentRepository;
    public Contractor createContractor(Long projectId, ContractorRequestDto contractorDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        // Map DTO to entity
        Contractor contractor = contractorMapper.toEntity(contractorDto);
        contractor.setProject(project);

        // Calculate totals
        double total = contractor.getTotal() != null ? contractor.getTotal() : 0.0;
        double paid = contractor.getContractorPaidAmount() != null ? contractor.getContractorPaidAmount() : 0.0;
        double remaining = total - paid;

        contractor.setReamingAmount(remaining);

        // Save contractor
        contractor = contractorRepository.save(contractor);

        // Create installment if paid > 0
        if (paid > 0) {
            ContractorInstallment installment = new ContractorInstallment();
            installment.setContractor(contractor);
            installment.setAmount(paid);
            installment.setContractorPayDate(LocalDate.now());
            installment.setContractorPayStatus(ExpensePayStatus.UPI); // set default enum

            contractorInstallmentRepository.save(installment);
        }

        return contractor;
    }

    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }

//    public Contractor addContractorInstallment(Long id, List<ContractorInstallmentDto> contractorInstallments) {
//        Contractor contractor = contractorRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Contractor id not found"));
//
//        List<ContractorInstallment> installments = new ArrayList<>();
//
//        for (ContractorInstallmentDto installmentDto : contractorInstallments) {
//            ContractorInstallment installment = new ContractorInstallment();
//            installment.setContractor(contractor);
//            installment.setContractorPayDate(installmentDto.getContractorPayDate());
//            installment.setAmount(installmentDto.getAmount());
//            installment.setRemark(installmentDto.getRemark());
//            installment.setContractorPayStatus(installmentDto.getContractorPayStatus());
//
//            // Ensure amount is not null before using it
//            double amount = installment.getAmount() != null ? installment.getAmount() : 0.0;
//
//            contractor.setContractorPaidAmount(contractor.getContractorPaidAmount() + amount);
//            contractor.setReamingAmount(contractor.getReamingAmount() - amount); // Fixed typo
//
//            installments.add(installment);
//        }
//
//        contractor.getContractorInstallments().addAll(installments);
//        contractorInstallmentRepository.saveAll(installments);
//
//        return contractorRepository.save(contractor);
//
//    }

    public AllContractorResponseDto addContractorInstallment(Long id, List<ContractorInstallmentDto> contractorInstallments) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contractor id not found"));

        List<ContractorInstallment> installments = new ArrayList<>();

        for (ContractorInstallmentDto installmentDto : contractorInstallments) {
            ContractorInstallment installment = new ContractorInstallment();
            installment.setContractor(contractor);
            installment.setContractorPayDate(installmentDto.getContractorPayDate());
            installment.setAmount(installmentDto.getAmount());
            installment.setRemark(installmentDto.getRemark());
            installment.setContractorPayStatus(installmentDto.getContractorPayStatus());

            double amount = installment.getAmount() != null ? installment.getAmount() : 0.0;

            contractor.setContractorPaidAmount(contractor.getContractorPaidAmount() + amount);
            contractor.setReamingAmount(contractor.getReamingAmount() - amount);

            installments.add(installment);
        }

        contractor.getContractorInstallments().addAll(installments);
        contractorInstallmentRepository.saveAll(installments);
        Contractor updated = contractorRepository.save(contractor);

        return contractorMapper.toDto(updated);
    }

    public Contractor updateOrAddContractorInstallment(Long id, List<ContractorInstallmentDto> contractorInstallments) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contractor ID not found"));

        List<ContractorInstallment> installments = new ArrayList<>();

        for (ContractorInstallmentDto installmentDto : contractorInstallments) {
            ContractorInstallment installment;

            if (installmentDto.getId() != null) {
                installment = contractorInstallmentRepository.findById(installmentDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Installment ID not found"));
            } else {
                installment = new ContractorInstallment();
                installment.setContractor(contractor);
            }

            installment.setContractorPayDate(installmentDto.getContractorPayDate());
            installment.setAmount(installmentDto.getAmount());
            installment.setRemark(installmentDto.getRemark());
            installment.setContractorPayStatus(installmentDto.getContractorPayStatus());

            installments.add(installment);
        }

        contractorInstallmentRepository.saveAll(installments);

        List<ContractorInstallment> installmentList = contractorInstallmentRepository.findByContractorId(id);
        double totalPaid = 0.0;

        for (ContractorInstallment installment : installmentList) {
            if (installment.getAmount() != null) {
                totalPaid += installment.getAmount();
            }
        }

        contractor.setContractorPaidAmount(totalPaid);
        contractor.setReamingAmount(contractor.getTotal() - totalPaid);

        return contractorRepository.save(contractor);
    }

    public ContractorInstallment getContractorInstallmentById(Long id) {
        return contractorInstallmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Installment Id Is not found"));
    }

    public AllContractorResponseDto updateContractorInstallments(Long contractorId, List<ContractorInstallmentDto> contractorInstallments) {
        Contractor contractor = contractorRepository.findById(contractorId)
                .orElseThrow(() -> new EntityNotFoundException("Contractor ID not found"));

        List<ContractorInstallment> updatedInstallments = new ArrayList<>();

        for (ContractorInstallmentDto installmentDto : contractorInstallments) {
            if (installmentDto.getId() == null) {
                throw new IllegalArgumentException("Installment ID is required for update");
            }

            ContractorInstallment installment = contractorInstallmentRepository.findById(installmentDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Installment ID not found"));

            installment.setContractorPayDate(installmentDto.getContractorPayDate());
            installment.setAmount(installmentDto.getAmount());
            installment.setRemark(installmentDto.getRemark());
            installment.setContractorPayStatus(installmentDto.getContractorPayStatus());

            updatedInstallments.add(installment);
        }

        contractorInstallmentRepository.saveAll(updatedInstallments);

        double totalPaid = contractorInstallmentRepository.findByContractorId(contractorId)
                .stream().mapToDouble(i -> i.getAmount() != null ? i.getAmount() : 0.0).sum();

        contractor.setContractorPaidAmount(totalPaid);
        contractor.setReamingAmount(contractor.getTotal() - totalPaid);

        Contractor saved = contractorRepository.save(contractor);
        return contractorMapper.toDto(saved);
    }


    public ContractorInstallment deleteContractorInstallment(Long id) {
        ContractorInstallment contractorInstallment = contractorInstallmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Installment Id not found"));
        contractorInstallmentRepository.deleteById(id);
        return contractorInstallment;
    }

    public List<AllContractorResponseDto> getContractorByProject(Long projectId) {
        List<Contractor> contractors = contractorRepository.findByProjectId(projectId);
        return contractors.stream().map(AllContractorResponseDto::fromEntity).collect(Collectors.toList());
    }

    public AllContractorResponseDto getContractorById(Long id) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contractor ID " + id + " Not Found"));

        // Calculate total paid and remaining amount
        double totalPaid = contractor.getContractorInstallments().stream()
                .mapToDouble(i -> i.getAmount() != null ? i.getAmount() : 0.0)
                .sum();
        contractor.setContractorPaidAmount(totalPaid);
        contractor.setReamingAmount(contractor.getTotal() - totalPaid);

        contractorRepository.save(contractor);

        return contractorMapper.toDto(contractor);
    }


    public AllContractorResponseDto deleteContractor(Long id) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contractor not found with id: " + id));

        // Capture DTO before deletion
        AllContractorResponseDto dto = contractorMapper.toDto(contractor);

        // Delete installments first
        for (ContractorInstallment installment : contractor.getContractorInstallments()) {
            contractorInstallmentRepository.delete(installment);
        }

        contractorRepository.delete(contractor);
        return dto;
    }

}
