package com.contractormanagemet.Contractor.Magement.Service;

import com.ibm.icu.text.RuleBasedNumberFormat;

import com.contractormanagemet.Contractor.Magement.Entity.BankNoc;
import com.contractormanagemet.Contractor.Magement.Repository.BankNocRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class BankNocService {
    @Autowired
    private BankNocRepository bankNocRepository;
//    public BankNoc createBankNoc(BankNoc bankNoc) {
//     return  bankNocRepository.save(bankNoc);
//    }

    public BankNoc createBankNoc(BankNoc bankNoc) {
        if (bankNoc.getTransactionAmount() != null) {
            try {
                String amount = bankNoc.getTransactionAmount().replaceAll(",", "");
                int amountValue = Integer.parseInt(amount);
                String amountInWords = convertNumberToWords(amountValue);
                bankNoc.setTransactionAmountWords(amountInWords + " only");
            } catch (NumberFormatException e) {
                bankNoc.setTransactionAmountWords("Invalid amount");
            }
        }
        return bankNocRepository.save(bankNoc);
    }

    private String convertNumberToWords(int number) {
        RuleBasedNumberFormat rbnf = new RuleBasedNumberFormat(Locale.ENGLISH, RuleBasedNumberFormat.SPELLOUT);
        return rbnf.format(number);
    }

    public BankNoc getBankNocByid(long id) {
        return bankNocRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Bank Id is not found"));
    }

    public List<BankNoc> getAllBankNoc() {
        return bankNocRepository.findAll();
    }

    public void deleteBankNocById(Long id) {
        bankNocRepository.deleteById(id);
    }
    public BankNoc updateBankNoc(Long id, BankNoc updatedBankNoc) {
        return bankNocRepository.findById(id).map(existingBankNoc -> {
            existingBankNoc.setBankName(updatedBankNoc.getBankName());
            existingBankNoc.setAddress(updatedBankNoc.getAddress());
            existingBankNoc.setCoustomername(updatedBankNoc.getCoustomername());
            existingBankNoc.setAggrementDate(updatedBankNoc.getAggrementDate());
            existingBankNoc.setFlatNo(updatedBankNoc.getFlatNo());
            existingBankNoc.setBuildingNo(updatedBankNoc.getBuildingNo());
            existingBankNoc.setStreetNo(updatedBankNoc.getStreetNo());
            existingBankNoc.setLocalityName(updatedBankNoc.getLocalityName());
            existingBankNoc.setAreaName(updatedBankNoc.getAreaName());
            existingBankNoc.setPincode(updatedBankNoc.getPincode());
            existingBankNoc.setCity(updatedBankNoc.getCity());
            existingBankNoc.setTransactionAmount(updatedBankNoc.getTransactionAmount());
            existingBankNoc.setFacvoringName(updatedBankNoc.getFacvoringName());
            existingBankNoc.setReciverBankName(updatedBankNoc.getReciverBankName());
            existingBankNoc.setBranchName(updatedBankNoc.getBranchName());
            existingBankNoc.setAcNO(updatedBankNoc.getAcNO());
            existingBankNoc.setIfsc(updatedBankNoc.getIfsc());
            return bankNocRepository.save(existingBankNoc);
           }).orElseThrow(() -> new EntityNotFoundException("BankNoc not found with id " + id));
    }


}