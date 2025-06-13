package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.LeadLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface LeadLogRepository extends JpaRepository<LeadLog, Long> {

    void deleteAllByLeadId(Long leadId);

    Collection<Object> findByLeadId(Long id);



    @Modifying
    @Transactional
    @Query("DELETE FROM LeadLog ll WHERE ll.lead.id = :leadId")
        void deleteByLeadId(@Param("leadId") Long leadId);




    List<LeadLog> findByLogDateIn(List<LocalDate> today);

    List<LeadLog> findByLogDate(LocalDate today);
}