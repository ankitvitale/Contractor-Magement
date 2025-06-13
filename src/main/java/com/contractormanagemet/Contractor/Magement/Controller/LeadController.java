package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.DTO.LeadDto.RemarkRequest;
import com.contractormanagemet.Contractor.Magement.Entity.Lead;
import com.contractormanagemet.Contractor.Magement.Entity.LeadLog;
import com.contractormanagemet.Contractor.Magement.Service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class LeadController {

    @Autowired
    private LeadService leadService;

//    @Autowired
//    private LeadLogService leadLogService;


    @PostMapping("/createNewLead")
    public ResponseEntity<Lead> addNewLead(@RequestBody Lead lead) {
        Lead savedLead = leadService.addNewLead(lead);
        return ResponseEntity.ok(savedLead);
    }

    @PostMapping("/{id}/addLogs")
    @PreAuthorize("hasAnyRole('Admin','Employee')")
    public ResponseEntity<Lead> addLogsToLead(@PathVariable Long id, @RequestBody List<LeadLog> leadLogs) {
        Lead updatedLead = leadService.addLogsToLead(id, leadLogs);
        return ResponseEntity.ok(updatedLead);
    }

    @GetMapping("/getlead/{id}")
    @PreAuthorize("hasAnyRole('Admin','Employee')")
    public ResponseEntity<Lead> getLeadById(@PathVariable Long id) {
        Lead lead = leadService.getLeadById(id); // Fetch the lead with logs
        return ResponseEntity.ok(lead);
    }

    @GetMapping("/getAllLeads")
    @PreAuthorize("hasAnyRole('Admin','Employee')")
    public ResponseEntity<List<Lead>> getAllLeads() {
        List<Lead> leads = leadService.getAllLeads();
        return ResponseEntity.ok(leads);
    }
    @DeleteMapping("/deleteLead/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteLead(@PathVariable Long id) {
        boolean isDeleted = leadService.deleteLead(id);
        if (isDeleted) {
            return ResponseEntity.ok("Lead deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found.");
        }
    }

    @PutMapping("/updateLead/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Lead> updateLead(@PathVariable Long id, @RequestBody Lead lead) {
        Lead updatedLead = leadService.updateLead(id, lead);
        return ResponseEntity.ok(updatedLead);
    }

    @PostMapping("/remark/{id}/remark")
    @PreAuthorize("hasAnyRole('Admin','Employee')")
    public ResponseEntity<Lead> addRemark(@PathVariable Long id, @RequestBody RemarkRequest remarkRequest) {
        Lead updatedLead = leadService.addRemark(id, remarkRequest.getRemark(), remarkRequest.getRemarkdate());
        return ResponseEntity.ok(updatedLead);
    }
    @GetMapping("/lead/alerts")
    public ResponseEntity<List<String>> getTodayAndSeventhDayAlerts() {
        List<String> alerts = leadService.getTodayCallMeAlerts();
        return ResponseEntity.ok(alerts);
    }
}




