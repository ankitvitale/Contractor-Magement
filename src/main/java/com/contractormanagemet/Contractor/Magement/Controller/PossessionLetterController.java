package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.Entity.PossessionLetter;
import com.contractormanagemet.Contractor.Magement.Service.PossessionLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PossessionLetterController {

       @Autowired
       PossessionLetterService possessionLetterService;

        @PostMapping("/createPossessionLetter")
        @PreAuthorize("hasRole('Admin')")
        public ResponseEntity<PossessionLetter> createPossessionLetter(@RequestBody PossessionLetter possessionLetter){
            PossessionLetter possessionLetter1=possessionLetterService.createPossessionLetter(possessionLetter);
            return ResponseEntity.ok(possessionLetter1);
       }

      @GetMapping("/PossessionLetter")
       @PreAuthorize("hasRole('Admin')")
       public List<PossessionLetter> getAllPossessionLetter(){
            return possessionLetterService.getAllPossessionLetter();

       }

    @GetMapping("/PossessionLetter/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PossessionLetter> getPossessionLetterById(@PathVariable Long id) {
        Optional<PossessionLetter> possessionLetter = possessionLetterService.getPossessionLetterById(id);
        if (possessionLetter.isPresent()) {
            return ResponseEntity.ok(possessionLetter.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/PossessionLetter/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> deletePossessionLetter(@PathVariable Long id) {
        possessionLetterService.deletePossessionLetterById(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
    @PutMapping("/PossessionLetter/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<PossessionLetter> updatePossessionLetter(
            @PathVariable Long id,
            @RequestBody PossessionLetter updatedPossessionLetter
    ) {
        PossessionLetter possessionLetter = possessionLetterService.updatePossessionLetter(id, updatedPossessionLetter);
        return ResponseEntity.ok(possessionLetter);
    }

}