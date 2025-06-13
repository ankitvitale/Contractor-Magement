package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM project WHERE id = :projectId", nativeQuery = true)
    void deleteProjectById(@Param("projectId") Long id);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM app_user_allowed_site WHERE allowed_site_id = :projectId", nativeQuery = true)
    void deleteAppUserAllowedSiteByProjectId(@Param("projectId") Long projectId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM expense WHERE project_id = :projectId", nativeQuery = true)
    void deleteExpensesByProjectId(@Param("projectId") Long projectId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM expense_installment WHERE expense_id IN (SELECT id FROM expense WHERE project_id = :projectId)", nativeQuery = true)
    void deleteExpenseInstallmentsByProjectId(@Param("projectId") Long projectId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM residency WHERE project_id = :projectId", nativeQuery = true)
    void deleteResidenciesByProjectId(@Param("projectId") Long projectId);
}
