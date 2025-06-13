package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.SuperisorDto.SuperisorResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Superisor;
import com.contractormanagemet.Contractor.Magement.Service.SuperisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuperisorController {

    @Autowired
    private SuperisorService superisorService;
    @GetMapping("/AllSuperisor")
    public ResponseEntity<List<SuperisorResponseDto>> getAllSuperisor(){
        return ResponseEntity.ok(superisorService.findAllSuperisor());
    }
}
