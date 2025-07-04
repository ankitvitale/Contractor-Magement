package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.LandExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandExpenseRepository extends JpaRepository<LandExpense, Long> {
    List<LandExpense> findByLandId(Long landId);
}
