package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.Residency;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResidencyRepository extends JpaRepository<Residency,Long> {
    List<Residency> findByProjectIdAndAvailabilityStatus(Long projectId, AvailabilityStatus availabilityStatus);

    List<Residency> findAllByProjectId(Long projectId);
    @Query("SELECT r.project.id, r.availabilityStatus, COUNT(r) FROM Residency r GROUP BY r.project.id, r.availabilityStatus")
    List<Object[]> countByProjectIdAndAvailabilityStatus();
}
