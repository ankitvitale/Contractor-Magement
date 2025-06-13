package com.contractormanagemet.Contractor.Magement.Repository;


import com.contractormanagemet.Contractor.Magement.Entity.PossessionLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PossessionRepository extends JpaRepository<PossessionLetter,Long> {
}