package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.Entity.Lead;

import com.contractormanagemet.Contractor.Magement.Entity.LeadLog;
import com.contractormanagemet.Contractor.Magement.Repository.LeadLogRepository;
import com.contractormanagemet.Contractor.Magement.Repository.LeadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadLogRepository leadLogRepository;

    public Lead addNewLead(Lead lead) {
        return leadRepository.save(lead);
    }

    public Lead addLogsToLead(Long id, List<LeadLog> leadLogs) {
        // Find the lead by ID or throw an exception if not found
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID: " + id));
        // Set the lead for each LeadLog
        for (LeadLog log : leadLogs) {
            log.setLead(lead); // Ensure Lead is set in each log
        }
        // Add the logs to the lead
        lead.getLeadLogs().addAll(leadLogs);
        // Update the lead's status based on the latest LeadLog status
        if (!leadLogs.isEmpty()) {
            LeadLog latestLog = leadLogs.get(leadLogs.size() - 1); // Get the latest added log
            lead.setStatus(latestLog.getStatus()); // Update the lead status
        }
        // Save the lead and the logs
        leadLogRepository.saveAll(leadLogs);
        return lead; // Return the updated lead
    }

    public Lead getLeadById(Long id) {
        return leadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID: " + id));
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public boolean deleteLead(Long id) {
        Optional<Lead> lead = leadRepository.findById(id);
        if (lead.isPresent()) {
            // First, delete all LeadLogs associated with the Lead
            leadLogRepository.deleteByLeadId(id); // Assuming you have this method in your LeadLog repository

            // Then, delete the Lead
            leadRepository.delete(lead.get());
            return true;
        }
        return false;
    }

    public Lead updateLead(Long id, Lead updatedLead) {
        // Find the existing lead by ID or throw an exception if not found
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lead not found with ID: " + id));

        // Update the lead details
        lead.setName(updatedLead.getName());
        lead.setJobTitle(updatedLead.getJobTitle());
        lead.setCompanyName(updatedLead.getCompanyName());
        lead.setEmail(updatedLead.getEmail());
        lead.setPhoneNumber(updatedLead.getPhoneNumber());
        lead.setFoundOn(updatedLead.getFoundOn());
        lead.setRemark(updatedLead.getRemark());
        lead.setRemarkdate(updatedLead.getRemarkdate());
        lead.setStatus(updatedLead.getStatus());

        // Update the lead logs if provided
        if (updatedLead.getLeadLogs() != null && !updatedLead.getLeadLogs().isEmpty()) {
            // Set the lead reference for each LeadLog and add them to the lead's logs
            for (LeadLog log : updatedLead.getLeadLogs()) {
                log.setLead(lead);  // Set the lead in each log
            }
            lead.getLeadLogs().clear();  // Clear the existing logs
            lead.getLeadLogs().addAll(updatedLead.getLeadLogs());  // Add the updated logs

            // Update the lead's status based on the latest LeadLog status
            LeadLog latestLog = updatedLead.getLeadLogs().get(updatedLead.getLeadLogs().size() - 1);  // Get the latest log
            lead.setStatus(latestLog.getStatus());  // Update the lead's status
        }

        // Save the updated lead and its logs (if any)
        leadLogRepository.saveAll(updatedLead.getLeadLogs());
        leadRepository.save(lead);

        return lead;  // Return the updated lead
    }

    public Lead addRemark(Long id, String remark, LocalDate remarkDate) {
        Optional<Lead> optionalLead = leadRepository.findById(id);
        if (optionalLead.isPresent()) {
            Lead lead = optionalLead.get();
            lead.setRemark(remark);
            lead.setRemarkdate(remarkDate); // Set the remark date
            return leadRepository.save(lead);
        } else {
            throw new RuntimeException("Lead not found with id: " + id);
        }
    }

//    public List<String> getTodayAlerts() {
//        LocalDate today = LocalDate.now();
//        List<LeadLog> logs = leadLogRepository.findByLogDate(today);
//
//        List<String> alerts = new ArrayList<>();
//        for (LeadLog log : logs) {
//            Lead lead = log.getLead();
//            String message = String.format("⚠️ Alert: Lead '%s' has status '%s' on %s. Remark: %s",
//                    lead.getName(), log.getStatus(), log.getLogDate(), log.getLead().getRemark());
//            alerts.add(message);
//        }
//        return alerts;
//    }

//    public List<String> getTodayAndSeventhDayAlerts() {
//        LocalDate today = LocalDate.now();
//        LocalDate sevenDaysAgo = today.minusDays(7);
//
//        List<LeadLog> logs = leadLogRepository.findByLogDateIn(List.of(today, sevenDaysAgo));
//        List<String> alerts = new ArrayList<>();
//
//        for (LeadLog log : logs) {
//            Lead lead = log.getLead();
//            String message = String.format(
//                    "⚠️ Alert: Lead '%s' has status '%s' on %s. Remark: %s",
//                    lead.getName(),
//                    log.getStatus(),
//                    log.getLogDate(),
//                    lead.getRemark()
//            );
//            alerts.add(message);
//        }
//
//        return alerts;
//    }


    public List<String> getTodayCallMeAlerts() {
        LocalDate today = LocalDate.now();

        // Only fetch logs with today's date
        List<LeadLog> logs = leadLogRepository.findByLogDate(today);
        List<String> alerts = new ArrayList<>();

        for (LeadLog log : logs) {
            Lead lead = log.getLead();
            String remark = lead.getRemark() != null ? lead.getRemark().toLowerCase() : "";

            // Check if remark contains "call me"
            if (remark.contains("call me")) {
                String message = String.format(
                        "📞 Reminder: Lead '%s' needs follow-up. Status: '%s', Date: %s, Remark: %s",
                        lead.getName(),
                        log.getStatus(),
                        log.getLogDate(),
                        lead.getRemark()
                );
                alerts.add(message);
            }
        }

        return alerts;
    }


}

