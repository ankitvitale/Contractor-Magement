package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.Entity.BankNoc;
import com.contractormanagemet.Contractor.Magement.Service.BankNocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankNocController {

    @Autowired
    private BankNocService bankNocService;

    @PostMapping("/createBankNoc")
    @PreAuthorize("hasRole('Admin')")
    public BankNoc createBankNoc(@RequestBody BankNoc bankNoc){
        return bankNocService.createBankNoc(bankNoc);
    }

    @GetMapping("/bankNoc/{id}")
    @PreAuthorize("hasRole('Admin')")
    public BankNoc getBankNocByid(@PathVariable("id") long id){
        return bankNocService.getBankNocByid(id);
    }

    @GetMapping("/bankNoc")
    @PreAuthorize("hasRole('Admin')")
    public List<BankNoc> getAlllBankNoc(){
        return bankNocService.getAllBankNoc();
    }

    @DeleteMapping("/bankNoc/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteBankNoc(@PathVariable("id") Long id) {
        bankNocService.deleteBankNocById(id);
    }


    @PutMapping("/bankNoc/{id}")
    @PreAuthorize("hasRole('Admin')")
    public BankNoc updateBankNoc(@PathVariable("id") Long id, @RequestBody BankNoc bankNoc) {
        return bankNocService.updateBankNoc(id, bankNoc);
    }

}