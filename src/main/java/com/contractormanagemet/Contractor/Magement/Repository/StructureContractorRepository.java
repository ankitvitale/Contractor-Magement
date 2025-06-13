package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.StructureContractor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StructureContractorRepository extends JpaRepository<StructureContractor,Long> {
    List<StructureContractor> findByProjectId(Long projectId);
}
