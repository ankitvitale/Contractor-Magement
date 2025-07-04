package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.Entity.DemandLetter;
import com.contractormanagemet.Contractor.Magement.Service.DemandLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DemandLetterController {

    @Autowired
    private DemandLetterService demandLetterService;

    // Create a DemandLetter
    @PostMapping("/createDemandLetter")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public DemandLetter createDemandLetter(@RequestBody DemandLetter demandLetter) {
        return demandLetterService.createDemandLetter(demandLetter);
    }

    // Get all DemandLetters
    @GetMapping("/getAllDemandLetters")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public List<DemandLetter> getAllDemandLetters() {
        return demandLetterService.getAllDemandLetters();
    }

    // Get DemandLetter by ID
    @GetMapping("/getDemandLetterById/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public DemandLetter getDemandLetterById(@PathVariable("id") Long id) {
        return demandLetterService.getDemandLetterById(id);
    }

    // Update DemandLetter
    @PutMapping("/updateDemandLetter/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public DemandLetter updateDemandLetter(@PathVariable("id") Long id, @RequestBody DemandLetter demandLetter) {
        return demandLetterService.updateDemandLetter(id, demandLetter);
    }

    // Delete DemandLetter
    @DeleteMapping("/deleteDemandLetter/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public String deleteDemandLetter(@PathVariable("id") Long id) {
        demandLetterService.deleteDemandLetter(id);
        return "DemandLetter with ID " + id + " has been deleted.";
    }
}