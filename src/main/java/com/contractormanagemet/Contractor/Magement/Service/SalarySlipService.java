package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.SalaryDto.SalarySlipRequest;
import com.contractormanagemet.Contractor.Magement.Entity.SalarySlip;
import com.contractormanagemet.Contractor.Magement.Exception.ResourceNotFoundException;
import com.contractormanagemet.Contractor.Magement.Repository.SalarySlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalarySlipService {

    @Autowired
    private SalarySlipRepository repository;

    public SalarySlip create(SalarySlipRequest request) {
        SalarySlip slip = new SalarySlip();
        mapRequestToEntity(request, slip);
        calculateTotals(slip);
        return repository.save(slip);
    }

    public SalarySlip getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salary Slip not found"));
    }

    public List<SalarySlip> getAll() {
        return repository.findAll();
    }

    public SalarySlip update(Long id, SalarySlipRequest request) {
        SalarySlip slip = getById(id);
        mapRequestToEntity(request, slip);
        calculateTotals(slip);
        return repository.save(slip);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void mapRequestToEntity(SalarySlipRequest request, SalarySlip slip) {
        slip.setEmployeeId(request.getEmployeeId());
        slip.setEmployeeName(request.getEmployeeName());
        slip.setDesignation(request.getDesignation());
        slip.setDepartment(request.getDepartment());
        slip.setMonth(request.getMonth());
        slip.setYear(request.getYear());
        slip.setPaidDays(request.getPaidDays());
        slip.setUanNo(request.getUanNo());
        slip.setBankAccountNo(request.getBankAccountNo());
        slip.setDateOfJoining(request.getDateOfJoining());

        slip.setBasic(request.getBasic());
        slip.setHra(request.getHra());
        slip.setBonus(request.getBonus());
        slip.setAllowance(request.getAllowance());

        slip.setPfAmount(request.getPfAmount());
        slip.setProfessionalTax(request.getProfessionalTax());
        slip.setOtherDeductions(request.getOtherDeductions());
        slip.setLoan(request.getLoan());
    }

    private void calculateTotals(SalarySlip slip) {
        double totalAddition = slip.getBasic() + slip.getHra() + slip.getBonus() + slip.getAllowance();
        double totalDeductions = slip.getPfAmount() + slip.getProfessionalTax() + slip.getOtherDeductions() + slip.getLoan();
        double netSalary = totalAddition - totalDeductions;

        slip.setTotalAddition(totalAddition);
        slip.setTotalDeductions(totalDeductions);
        slip.setNetSalary(netSalary);
    }
}
