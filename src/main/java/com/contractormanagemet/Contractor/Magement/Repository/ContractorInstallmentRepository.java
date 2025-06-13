package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.ContractorInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractorInstallmentRepository extends JpaRepository<ContractorInstallment,Long> {
    List<ContractorInstallment> findByContractorId(Long id);
}