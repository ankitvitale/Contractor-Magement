package com.contractormanagemet.Contractor.Magement.Repository;


import com.contractormanagemet.Contractor.Magement.Entity.DemandLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandLetterRepository extends JpaRepository<DemandLetter, Long> {
}