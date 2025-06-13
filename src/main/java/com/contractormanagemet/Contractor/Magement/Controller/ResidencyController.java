package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.DTO.ResidencyDto.ResidencyRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ResidencyDto.ResidencyResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Residency;
import com.contractormanagemet.Contractor.Magement.Service.ResidencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResidencyController {

    @Autowired
    private ResidencyService residencyService;

    @PostMapping("/createResidency")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Residency> createResidency(@RequestBody ResidencyRequestDto residencyDto){
        Residency createResidency= residencyService.createResidency(residencyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createResidency);

    }

    @GetMapping("/allResidency")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Residency>> getAllResidencies() {
        List<Residency> residencies = residencyService.getAllResidencies();
        return ResponseEntity.ok(residencies);
    }

    @GetMapping("/allResidencybyid/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Residency> getResidencyById(@PathVariable Long id) {
        Residency residency = residencyService.getResidencyById(id);
        return ResponseEntity.ok(residency);
    }
    @GetMapping("/project/{projectId}")
//@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<ResidencyResponseDto>> getResidenciesByProjectId(@PathVariable Long projectId) {
        List<ResidencyResponseDto> residencies = (List<ResidencyResponseDto>) residencyService.getResidenciesByProjectId(projectId);
        return ResponseEntity.ok(residencies);
    }
    @PutMapping("/updateresidency/{id}")
    public ResponseEntity<Residency> updateResidency(@PathVariable Long id, @RequestBody ResidencyRequestDto residencyDto) {
        Residency updatedResidency = residencyService.updateResidency(id, residencyDto);
        return ResponseEntity.ok(updatedResidency);
    }

    @DeleteMapping("/deleteResidency/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Residency> deleteResidency(@PathVariable  Long id){
        Residency residency=residencyService.deleteResidency(id);
        return ResponseEntity.ok(residency);

    }

    @GetMapping("/residenciesByProject/{projectId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Residency>> getResidenciesByProject(@PathVariable Long projectId) {
        List<Residency> residencies = residencyService.getResidenciesByProject(projectId);
        return ResponseEntity.ok(residencies);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Map<Long, Map<String, Long>>> countResidenciesByProjectAndStatus() {
        Map<Long, Map<String, Long>> counts = residencyService.countResidenciesByProjectAndStatus();
        return ResponseEntity.ok(counts);
    }
}
