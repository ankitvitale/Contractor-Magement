package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.Entity.OfficeExpenses;
import com.contractormanagemet.Contractor.Magement.Repository.OfficeExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeExpensesService {

    @Autowired
    private OfficeExpensesRepository officeExpensesRepository;

    public OfficeExpenses createExpenses(OfficeExpenses officeExpenses) {
        return officeExpensesRepository.save(officeExpenses);
    }

    public List<OfficeExpenses> getAllExpenses() {
        return officeExpensesRepository.findAll();
    }

    public OfficeExpenses getExpenseById(Long id) {
        return officeExpensesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));
    }

    public OfficeExpenses updateExpense(Long id, OfficeExpenses officeExpenses) {
        OfficeExpenses existing = getExpenseById(id);
        existing.setDate(officeExpenses.getDate());
        existing.setReciverName(officeExpenses.getReciverName());
        existing.setGiverName(officeExpenses.getGiverName());
        existing.setAmount(officeExpenses.getAmount());
        existing.setRemark(officeExpenses.getRemark());
        return officeExpensesRepository.save(existing);
    }


    public void deleteExpense(Long id) {
        officeExpensesRepository.deleteById(id);
    }
}


