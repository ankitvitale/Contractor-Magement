package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.SalarySlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarySlipRepository extends JpaRepository<SalarySlip, Long> {}
