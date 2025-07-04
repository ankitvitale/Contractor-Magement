package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.SubAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubAdminRepository extends JpaRepository<SubAdmin,Long> {
    SubAdmin findByEmail(String email);


}
