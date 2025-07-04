package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.LandExpenceDto.LandExpenseDto;
import com.contractormanagemet.Contractor.Magement.DTO.LandExpenceDto.LandExpenseResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Employee;
import com.contractormanagemet.Contractor.Magement.Entity.Land;
import com.contractormanagemet.Contractor.Magement.Entity.LandExpense;
import com.contractormanagemet.Contractor.Magement.Entity.SubAdmin;
import com.contractormanagemet.Contractor.Magement.Repository.EmployeeRepository;
import com.contractormanagemet.Contractor.Magement.Repository.LandExpenseRepository;
import com.contractormanagemet.Contractor.Magement.Repository.LandRepository;
import com.contractormanagemet.Contractor.Magement.Repository.SubAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LandExpenseService {

    @Autowired
    private LandExpenseRepository landExpenseRepository;

    @Autowired
    private LandRepository landRepository;
    @Autowired
    private SubAdminRepository subAdminRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    public LandExpenseDto createExpense(Long landId, LandExpenseDto dto) {
        Optional<Land> land = landRepository.findById(landId);
        if (land.isEmpty()) return null;

        LandExpense expense = new LandExpense();
        expense.setExpenseName(dto.getExpenseName());
        expense.setAmount(dto.getAmount());
        expense.setDate(LocalDate.now());
        expense.setLand(land.get());

        LandExpense saved = landExpenseRepository.save(expense);

        return new LandExpenseDto(saved.getId(), saved.getExpenseName(), saved.getAmount(), saved.getDate(),saved.getUpdatedBy());
    }


    public List<LandExpense> getExpensesByLandId(Long landId) {
        return landExpenseRepository.findByLandId(landId);
    }

//    public LandExpense updateExpense(Long id, LandExpenseDto updatedExpense) {
//        LandExpense existing = landExpenseRepository.findById(id).orElse(null);
//        if (existing == null) {
//            return null;
//        }
//
//        existing.setExpenseName(updatedExpense.getExpenseName());
//        existing.setAmount(updatedExpense.getAmount());
//        existing.setDate(updatedExpense.getDate()); // ✅ Set date too
//
//        return landExpenseRepository.save(existing);
//    }


    public LandExpense updateExpense(Long id, LandExpenseDto updatedExpense) {
        LandExpense existing = landExpenseRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        // Set updated fields
        existing.setExpenseName(updatedExpense.getExpenseName());
        existing.setAmount(updatedExpense.getAmount());
        existing.setDate(updatedExpense.getDate());

        // 🔐 Get logged-in user details
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        Employee employee = employeeRepository.findByEmail(email);
        SubAdmin subAdmin = subAdminRepository.findByEmail(email);

        String fullNameWithEmail;

        if (employee != null) {
            fullNameWithEmail = employee.getName() + " (" + employee.getEmail() + ")";
        } else if (subAdmin != null) {
            fullNameWithEmail = subAdmin.getName() + " (" + subAdmin.getEmail() + ")";
        } else if (isAdmin) {
            fullNameWithEmail = "Admin (" + email + ")";
        } else {
            fullNameWithEmail = "edit by Admin(" + email + ")";
        }

        // Set updatedBy
        existing.setUpdatedBy(fullNameWithEmail);

        return landExpenseRepository.save(existing);
    }


    public boolean deleteExpense(Long id) {
        if (!landExpenseRepository.existsById(id)) {
            return false;
        }
        landExpenseRepository.deleteById(id);
        return true;
    }

    public LandExpenseResponseDto getExpensesByLand(Long landId) {
        List<LandExpense> expenses = landExpenseRepository.findByLandId(landId);

        List<LandExpenseDto> expenseDtos = expenses.stream()
                .map(e -> new LandExpenseDto(e.getId(), e.getExpenseName(), e.getAmount(),e.getDate(),e.getUpdatedBy()))
                .collect(Collectors.toList());

        double total = expenseDtos.stream()
                .mapToDouble(LandExpenseDto::getAmount)
                .sum();

        return new LandExpenseResponseDto(expenseDtos, total);
    }

    public LandExpense getExpenseById(Long id) {
        return landExpenseRepository.findById(id).orElse(null);
    }

}
