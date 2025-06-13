package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.Entity.AlotmentLetter;
import com.contractormanagemet.Contractor.Magement.Service.AlotmentLetterService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class AlotmentLetterController {


    @Autowired
    private AlotmentLetterService alotmentLetterService;

    // GET all Alotment Letters
    @GetMapping("/getAllAlotmentLetters")
    @PreAuthorize("hasRole('Admin')")
    public List<AlotmentLetter> getAllAlotmentLetters() {
        return alotmentLetterService.getAllAlotmentLetters();
    }

    // GET Alotment Letter by ID
    @GetMapping("/AlotmentLetterById/{id}")
    @PreAuthorize("hasRole('Admin')")
    public AlotmentLetter getAlotmentLetterById(@PathVariable Long id) {
        return alotmentLetterService.getAlotmentLetterById(id);
    }

    // POST: Create new Alotment Letter
    @PostMapping("/createAlotmentLetter")
    @PreAuthorize("hasRole('Admin')")
    public AlotmentLetter createAlotmentLetter(@RequestBody AlotmentLetter alotmentLetter) {
        return alotmentLetterService.createAlotmentLetter(alotmentLetter);
    }

    // PUT: Update existing Alotment Letter
    @PutMapping("/updateAlotmentLetter/{id}")
    @PreAuthorize("hasRole('Admin')")
    public AlotmentLetter updateAlotmentLetter(@PathVariable Long id, @RequestBody AlotmentLetter alotmentLetter) {
        return alotmentLetterService.updateAlotmentLetter(id, alotmentLetter);
    }

    // DELETE: Remove an Alotment Letter
    @DeleteMapping("/deleteAlotmentLetter/{id}")
    @PreAuthorize("hasRole('Admin')")
    public String deleteAlotmentLetter(@PathVariable Long id) {
        alotmentLetterService.deleteAlotmentLetter(id);
        return "Alotment Letter with ID " + id + " has been deleted.";
    }
}