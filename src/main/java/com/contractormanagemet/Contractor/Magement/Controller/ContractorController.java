package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.AllContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorInstallmentDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.ContractorDto.ContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Contractor;
import com.contractormanagemet.Contractor.Magement.Entity.ContractorInstallment;
import com.contractormanagemet.Contractor.Magement.Service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/contractor/{projectId}")
    public ResponseEntity<Contractor> createContractor(
            @PathVariable Long projectId,
            @RequestBody ContractorRequestDto contractorDto) {
        Contractor newContractor = contractorService.createContractor(projectId, contractorDto);
        return new ResponseEntity<>(newContractor, HttpStatus.CREATED);
    }

    @GetMapping("/contractor")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Contractor>> getAllContractors() {
        List<Contractor> contractors = contractorService.getAllContractors();
        return ResponseEntity.ok(contractors);
    }

//    @PostMapping("/{id}/contractorInstallment")
//    @PreAuthorize("hasAnyRole('Admin','AppUser')")
//    public ResponseEntity<Contractor> addContractorInstallment(
//            @PathVariable Long id,
//            @RequestBody List<ContractorInstallmentDto> contractorInstallments) {
//        Contractor updatedContractor = contractorService.addContractorInstallment(id, contractorInstallments);
//        return ResponseEntity.ok(updatedContractor);
//    }

    @PostMapping("/{id}/contractorInstallment")
    @PreAuthorize("hasAnyRole('Admin','AppUser')")
    public ResponseEntity<AllContractorResponseDto> addContractorInstallment(
            @PathVariable Long id,
            @RequestBody List<ContractorInstallmentDto> contractorInstallments) {
        AllContractorResponseDto dto = contractorService.addContractorInstallment(id, contractorInstallments);
        return ResponseEntity.ok(dto);
    }



    @PostMapping("/{id}/updatCeontractorInstallment")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Contractor> updateOrAddContractorInstallment(@PathVariable Long id, @RequestBody List<ContractorInstallmentDto> contractorInstallments){
        Contractor updateContractorInstallment=contractorService.updateOrAddContractorInstallment(id,contractorInstallments);
        return ResponseEntity.ok(updateContractorInstallment);
    }
    @GetMapping("/getSingleInstallmentById/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<ContractorInstallment> getContractorInstallmentById(@PathVariable Long id){
        ContractorInstallment contractorInstallment=contractorService.getContractorInstallmentById(id);
        return   ResponseEntity.ok(contractorInstallment);
    }
    @PutMapping("/updateContractorInstallment/{contractorId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<AllContractorResponseDto> updateContractorInstallments(
            @PathVariable Long contractorId,
            @RequestBody List<ContractorInstallmentDto> contractorInstallments) {
        AllContractorResponseDto dto = contractorService.updateContractorInstallments(contractorId, contractorInstallments);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deleteContractorInstallment/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Map<String, String>> deleteContractorInstallment(@PathVariable("id") Long id) {
        contractorService.deleteContractorInstallment(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Deleted successfully"));
    }


    @GetMapping("/{projectId}/Contractor")
    @PreAuthorize("hasAnyRole('Admin','AppUser')")
    public ResponseEntity<List<AllContractorResponseDto>> getContractorByProject(@PathVariable("projectId")  Long projectId) {
        List<AllContractorResponseDto> contractors = contractorService.getContractorByProject(projectId);
        return ResponseEntity.ok(contractors);
    }



    @GetMapping("/Contractor/{id}")
    @PreAuthorize("hasAnyRole('Admin','AppUser')")
    public ResponseEntity<AllContractorResponseDto> getContractorById(@PathVariable("id") Long id) {
        AllContractorResponseDto contractorDto = contractorService.getContractorById(id);
        return ResponseEntity.ok(contractorDto);
    }


    @DeleteMapping("/deleteContractor/{id}")
    @PreAuthorize("hasAnyRole('Admin','AppUser')")
    public ResponseEntity<AllContractorResponseDto> deleteContractor(@PathVariable("id") Long id) {
        AllContractorResponseDto dto = contractorService.deleteContractor(id);
        return ResponseEntity.ok(dto);
    }

}
