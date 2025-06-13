package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.DTO.StockDto.StockUsageResponse;
import com.contractormanagemet.Contractor.Magement.Entity.StockUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockUsageRepository extends JpaRepository<StockUsage, Long> {
    List<StockUsageResponse> findByProductId(Long productId);
}