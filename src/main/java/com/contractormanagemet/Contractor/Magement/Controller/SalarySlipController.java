package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.SalaryDto.SalarySlipRequest;
import com.contractormanagemet.Contractor.Magement.Entity.SalarySlip;
import com.contractormanagemet.Contractor.Magement.Service.SalarySlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salary-slips")
public class SalarySlipController {

    @Autowired
    private SalarySlipService service;

    @PostMapping
    public ResponseEntity<SalarySlip> create(@RequestBody SalarySlipRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalarySlip> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SalarySlip>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalarySlip> update(@PathVariable("id") Long id, @RequestBody SalarySlipRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
