package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto.ContractorListWithTotalDto;
import com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto.StructureContractorRequestDto;
import com.contractormanagemet.Contractor.Magement.DTO.StructureContractorDto.StructureContractorResponseDto;
import com.contractormanagemet.Contractor.Magement.Service.StructureContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StructureContractorController {

    @Autowired
    private StructureContractorService contractorService;

    @PostMapping("/add")
    public ResponseEntity<StructureContractorResponseDto> createContractor(
            @RequestBody StructureContractorRequestDto dto) {
        StructureContractorResponseDto savedDto = contractorService.createContractor(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("/show-StructureContractor")
    public ResponseEntity<List<StructureContractorResponseDto>> getAllContractors() {
        return ResponseEntity.ok(contractorService.getAllContractors());
    }

//    @GetMapping("/show-StructureContractor/by-project/{projectId}")
//    public ResponseEntity<List<StructureContractorResponseDto>> getByProjectId(@PathVariable Long projectId) {
//        return ResponseEntity.ok(contractorService.getContractorsByProjectId(projectId));
//    }

    @GetMapping("/show-StructureContractor/by-project/{projectId}")
    public ResponseEntity<ContractorListWithTotalDto> getByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(contractorService.getContractorsByProjectId(projectId));
    }

    @GetMapping("/structure-contractor/{id}")
    public ResponseEntity<StructureContractorResponseDto> getContractorById(@PathVariable Long id) {
        StructureContractorResponseDto dto = contractorService.getContractorById(id);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/update-StructureContractor/{id}")
    public ResponseEntity<StructureContractorResponseDto> updateContractor(
            @PathVariable Long id,
            @RequestBody StructureContractorRequestDto dto) {
        StructureContractorResponseDto updated = contractorService.updateContractor(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete-StructureContractor/{id}")
    public ResponseEntity<String> deleteContractor(@PathVariable Long id) {
        contractorService.deleteContractor(id);
        return ResponseEntity.ok("Contractor deleted successfully");
    }


}
