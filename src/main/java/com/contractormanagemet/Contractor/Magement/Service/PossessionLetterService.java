package com.contractormanagemet.Contractor.Magement.Service;


import com.contractormanagemet.Contractor.Magement.Entity.PossessionLetter;
import com.contractormanagemet.Contractor.Magement.Repository.PossessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PossessionLetterService {
    @Autowired
    private PossessionRepository possessionRepository;
    public PossessionLetter createPossessionLetter(PossessionLetter possessionLetter) {
        return possessionRepository.save(possessionLetter);
    }

    public List<PossessionLetter> getAllPossessionLetter() {
        return possessionRepository.findAll();
    }

    public void deletePossessionLetterById(Long id) {
        PossessionLetter possessionLetter = possessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID not found"));

        possessionRepository.deleteById(id);
    }

    public PossessionLetter updatePossessionLetter(Long id, PossessionLetter updatedPossessionLetter) {
        PossessionLetter existingPossessionLetter = possessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PossessionLetter with ID " + id + " not found"));

        existingPossessionLetter.setFromName(updatedPossessionLetter.getFromName());
        existingPossessionLetter.setToName(updatedPossessionLetter.getToName());
        existingPossessionLetter.setName(updatedPossessionLetter.getName());
        existingPossessionLetter.setFlatNo(updatedPossessionLetter.getFlatNo());
        existingPossessionLetter.setResidencyName(updatedPossessionLetter.getResidencyName());
        existingPossessionLetter.setAddress(updatedPossessionLetter.getAddress());
        // If updated date is provided, set it; else keep old one
        if (updatedPossessionLetter.getDate() != null) {
            existingPossessionLetter.setDate(updatedPossessionLetter.getDate());
        }


        return possessionRepository.save(existingPossessionLetter);
    }

    public Optional<PossessionLetter> getPossessionLetterById(Long id) {
        return possessionRepository.findById(id);

    }
}