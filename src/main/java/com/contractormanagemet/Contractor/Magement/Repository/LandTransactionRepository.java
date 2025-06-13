package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.LandTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandTransactionRepository extends JpaRepository<LandTransaction,Long> {
    List<LandTransaction> findAllByPartnerLandId(Long landId);
    
    List<LandTransaction> findAllByPerson_Id(Long id);

    List<LandTransaction> findAllByPartnerId(Long partnerId);
}
