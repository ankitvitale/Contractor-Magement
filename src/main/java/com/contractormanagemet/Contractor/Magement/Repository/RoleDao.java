package com.contractormanagemet.Contractor.Magement.Repository;


import com.contractormanagemet.Contractor.Magement.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}