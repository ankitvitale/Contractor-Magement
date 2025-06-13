package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.Superisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperisorRepository extends JpaRepository<Superisor,Long> {
    Superisor findByEmail(String email);
}
