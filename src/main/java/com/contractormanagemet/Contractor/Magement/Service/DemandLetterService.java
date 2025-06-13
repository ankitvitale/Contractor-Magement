package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.Entity.DemandLetter;
import com.contractormanagemet.Contractor.Magement.Repository.DemandLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandLetterService {

    @Autowired
    private DemandLetterRepository demandLetterRepository;

    public List<DemandLetter> getAllDemandLetters() {
        return demandLetterRepository.findAll();
    }

    public DemandLetter getDemandLetterById(Long id) {
        return demandLetterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demand Letter not found with ID: " + id));
    }

    public DemandLetter createDemandLetter(DemandLetter demandLetter) {
        return demandLetterRepository.save(demandLetter);
    }

    public DemandLetter updateDemandLetter(Long id, DemandLetter demandLetter) {
        DemandLetter existingDemandLetter = getDemandLetterById(id);
        existingDemandLetter.setName(demandLetter.getName());
        existingDemandLetter.setFaltno(demandLetter.getFaltno());
        existingDemandLetter.setAmount(demandLetter.getAmount());
        existingDemandLetter.setSitename(demandLetter.getSitename());
        existingDemandLetter.setFavorOf(demandLetter.getFavorOf());
        existingDemandLetter.setBankName(demandLetter.getBankName());
        existingDemandLetter.setBranch(demandLetter.getBranch());
        existingDemandLetter.setAcNo(demandLetter.getAcNo());
        return demandLetterRepository.save(existingDemandLetter);
    }

    public void deleteDemandLetter(Long id) {
        demandLetterRepository.deleteById(id);
    }
}