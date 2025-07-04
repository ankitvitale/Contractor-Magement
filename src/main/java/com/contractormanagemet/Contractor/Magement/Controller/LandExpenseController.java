package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.LandExpenceDto.LandExpenseDto;
import com.contractormanagemet.Contractor.Magement.DTO.LandExpenceDto.LandExpenseResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Land;
import com.contractormanagemet.Contractor.Magement.Entity.LandExpense;
import com.contractormanagemet.Contractor.Magement.Repository.LandExpenseRepository;
import com.contractormanagemet.Contractor.Magement.Repository.LandRepository;
import com.contractormanagemet.Contractor.Magement.Service.LandExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/land-expenses")
@PreAuthorize("hasAnyRole('Admin','SubAdmin')")
public class LandExpenseController {

    @Autowired
    private LandExpenseService landExpenseService;
    @PostMapping("/{landId}")
    public ResponseEntity<LandExpenseDto> createExpense(@PathVariable Long landId, @RequestBody LandExpenseDto dto) {
        LandExpenseDto created = landExpenseService.createExpense(landId, dto);
        if (created == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(created);
    }


    @GetMapping("/{landId}")
    public ResponseEntity<LandExpenseResponseDto> getExpensesByLand(@PathVariable Long landId) {
        LandExpenseResponseDto response = landExpenseService.getExpensesByLand(landId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/GetById/{id}")
    public ResponseEntity<LandExpense> getExpenseById(@PathVariable Long id) {
        LandExpense expense = landExpenseService.getExpenseById(id);
        if (expense == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandExpense> updateExpense(@PathVariable Long id, @RequestBody LandExpenseDto expenseDetails) {
        LandExpense updated = landExpenseService.updateExpense(id, expenseDetails);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        boolean deleted = landExpenseService.deleteExpense(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
