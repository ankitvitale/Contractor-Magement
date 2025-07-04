package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
}
