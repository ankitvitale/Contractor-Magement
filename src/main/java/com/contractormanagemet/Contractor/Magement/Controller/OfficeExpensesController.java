package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.Entity.OfficeExpenses;
import com.contractormanagemet.Contractor.Magement.Service.OfficeExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office-expenses")
@PreAuthorize("hasRole('Admin')")
public class OfficeExpensesController {

    @Autowired
    private OfficeExpensesService officeExpensesService;

    @PostMapping("/create")
    public ResponseEntity<OfficeExpenses> createOfficeExpenses(@RequestBody OfficeExpenses officeExpenses) {
        OfficeExpenses created = officeExpensesService.createExpenses(officeExpenses);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<OfficeExpenses>> getAllExpenses() {
        return ResponseEntity.ok(officeExpensesService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeExpenses> getExpenseById(@PathVariable Long id) {
        OfficeExpenses expense = officeExpensesService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OfficeExpenses> updateExpense(@PathVariable Long id, @RequestBody OfficeExpenses officeExpenses) {
        OfficeExpenses updated = officeExpensesService.updateExpense(id, officeExpenses);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        officeExpensesService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
