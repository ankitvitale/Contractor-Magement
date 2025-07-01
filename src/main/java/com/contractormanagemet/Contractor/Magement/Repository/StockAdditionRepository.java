package com.contractormanagemet.Contractor.Magement.Repository;


import com.contractormanagemet.Contractor.Magement.Entity.StockAddition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAdditionRepository extends JpaRepository<StockAddition,Long> {
}
