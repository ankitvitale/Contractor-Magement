package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.Entity.Admin;
import com.contractormanagemet.Contractor.Magement.Entity.Employee;
import com.contractormanagemet.Contractor.Magement.Entity.OfficeExpenses;
import com.contractormanagemet.Contractor.Magement.Entity.SubAdmin;
import com.contractormanagemet.Contractor.Magement.Repository.AdminRepository;
import com.contractormanagemet.Contractor.Magement.Repository.EmployeeRepository;
import com.contractormanagemet.Contractor.Magement.Repository.OfficeExpensesRepository;
import com.contractormanagemet.Contractor.Magement.Repository.SubAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeExpensesService {

    @Autowired
    private OfficeExpensesRepository officeExpensesRepository;

    @Autowired
    private SubAdminRepository subAdminRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

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




//    public OfficeExpenses updateExpense(Long id, OfficeExpenses officeExpenses) {
//        OfficeExpenses existing = getExpenseById(id);
//
//        existing.setDate(officeExpenses.getDate());
//        existing.setReciverName(officeExpenses.getReciverName());
//        existing.setGiverName(officeExpenses.getGiverName());
//        existing.setAmount(officeExpenses.getAmount());
//        existing.setRemark(officeExpenses.getRemark());
//
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication()
//                .getAuthorities()
//                .stream()
//                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//
//        Employee employee = employeeRepository.findByEmail(email);
//        SubAdmin subAdmin = subAdminRepository.findByEmail(email);
//
//        String fullNameWithEmail = null;
//
//        if (employee != null) {
//            fullNameWithEmail = employee.getName() + " (" + employee.getEmail() + ")";
//        } else if (subAdmin != null) {
//            fullNameWithEmail = subAdmin.getName() + " (" + subAdmin.getEmail() + ")";
//        } else if (isAdmin) {
//            fullNameWithEmail = null; // Or use: fullNameWithEmail = "Admin";
//        } else {
//            fullNameWithEmail = "Unknown User (" + email + ")";
//            // Or optionally, log and skip setting updatedBy
//        }
//
//        existing.setUpdatedBy(fullNameWithEmail);
//       existing.setDate(LocalDate.now());
//
//        return officeExpensesRepository.save(existing);
//    }
public OfficeExpenses updateExpense(Long id, OfficeExpenses officeExpenses) {
    OfficeExpenses existing = getExpenseById(id);

    existing.setDate(officeExpenses.getDate());
    existing.setReciverName(officeExpenses.getReciverName());
    existing.setGiverName(officeExpenses.getGiverName());
    existing.setAmount(officeExpenses.getAmount());
    existing.setRemark(officeExpenses.getRemark());

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    boolean isAdmin = SecurityContextHolder.getContext().getAuthentication()
            .getAuthorities()
            .stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

    Employee employee = employeeRepository.findByEmail(email);
    SubAdmin subAdmin = subAdminRepository.findByEmail(email);

    String fullNameWithEmail = null;

    if (employee != null) {
        fullNameWithEmail = employee.getName() + " (" + employee.getEmail() + ")";
    } else if (subAdmin != null) {
        fullNameWithEmail = subAdmin.getName() + " (" + subAdmin.getEmail() + ")";
    } else if (isAdmin) {
        fullNameWithEmail = "Admin (" + email + ")";
    } else {
        fullNameWithEmail = "Edit by Admin (" + email + ")";
    }

    existing.setUpdatedBy(fullNameWithEmail);
    existing.setUpdatedAt(LocalDateTime.now()); // <-- Set current date & time

    return officeExpensesRepository.save(existing);
}


    public void deleteExpense(Long id) {
        officeExpensesRepository.deleteById(id);
    }
}


