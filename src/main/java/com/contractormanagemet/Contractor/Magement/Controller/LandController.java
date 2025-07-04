package com.contractormanagemet.Contractor.Magement.Controller;


import com.contractormanagemet.Contractor.Magement.DTO.LandTransactionDto;
import com.contractormanagemet.Contractor.Magement.DTO.PartnerDto;
import com.contractormanagemet.Contractor.Magement.DTO.PartnerWithTransactionsDto;
import com.contractormanagemet.Contractor.Magement.DTO.PurchaserWithTransactionsDto;
import com.contractormanagemet.Contractor.Magement.DTO.RequestDTO.LandRequestDTO;
import com.contractormanagemet.Contractor.Magement.DTO.ResponseDTO.LandTransactionsResponseDto;
import com.contractormanagemet.Contractor.Magement.Entity.Land;
import com.contractormanagemet.Contractor.Magement.Entity.LandTransaction;
import com.contractormanagemet.Contractor.Magement.Service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LandController {
    @Autowired
    private LandService landService;
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<Land> createLand(@RequestBody LandRequestDTO landRequestDTO){
        Land createLand=landService.createLand(landRequestDTO);
        return ResponseEntity.ok(createLand);
    }

    @GetMapping("/getAllland")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<List<Land>> getAllLands() {
        List<Land> lands = landService.getAllLands();
        return ResponseEntity.ok(lands);
    }

    @GetMapping("/land/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<Land> getLandById(@PathVariable("id") Long id) {
        Land lands = landService.getLandById(id);
        return ResponseEntity.ok(lands);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<Land> updateLand(@PathVariable("id") Long id, @RequestBody LandRequestDTO landRequestDto) {
        Land updatedLand = landService.updateLand(id, landRequestDto);
        return ResponseEntity.ok(updatedLand);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<String> deleteLand(@PathVariable("id") Long id) {
        landService.deleteLand(id);
        return ResponseEntity.status(HttpStatus.OK).body("Land with ID " + id + " has been deleted successfully.");
    }

    //partner Add Payment
    @PostMapping("/addpayment/partner/{partnerId}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<LandTransaction> addpayment(@RequestBody LandTransactionDto landTransactionDto, @PathVariable("partnerId") Long partnerId) {
        LandTransaction landTransaction = landService.addPayment(landTransactionDto, partnerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(landTransaction);
    }

    //update partener payment
    @PutMapping("/UpdatePartner/payment/{transactionId}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<LandTransaction> updatePayment(@RequestBody LandTransactionDto landTransactionDto, @PathVariable("transactionId") Long transactionId) {
        LandTransaction updatedTransaction = landService.updatePayment(landTransactionDto, transactionId);
        return ResponseEntity.ok(updatedTransaction);
    }

    //purchaser Add Payment
    @PostMapping("/addpayment/purchaser/{purchaserId}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<LandTransaction> addPaymentToPurchaser(
            @RequestBody LandTransactionDto landTransactionDto,
            @PathVariable("purchaserId") Long purchaserId
    ) {
        LandTransaction landTransaction = landService.addPaymentToPurchaser(landTransactionDto, purchaserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(landTransaction);
    }

    @GetMapping("/land/{id}/partners-with-transactions")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<List<PartnerWithTransactionsDto>> getPartnersWithTransactions(@PathVariable("id") Long id) {
        List<PartnerWithTransactionsDto> partners = landService.getPartnerTransactions(id);
        return ResponseEntity.ok(partners);
    }
    @GetMapping("/land/{id}/purchaser-with-transactions")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<PurchaserWithTransactionsDto> getPurchaserWithTransactions(@PathVariable("id") Long id) {
        PurchaserWithTransactionsDto purchaser = landService.getPurchaserTransaction(id);
        if (purchaser == null) {
            return ResponseEntity.notFound().build(); // Return 404 if no purchaser or transactions found
        }
        return ResponseEntity.ok(purchaser);
    }

    //show list of Partners paymenet
    @GetMapping("/land/{landId}/partners")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<List<PartnerWithTransactionsDto>> getPartnersByLand(@PathVariable("landId") Long landId) {
        List<PartnerWithTransactionsDto> partners = landService.getPartnersByLandId(landId);
        return ResponseEntity.ok(partners);
    }

    // show the single Partner payment with single transation
    @GetMapping("/SinglePartnerPaymentById/{id}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<LandTransactionDto> getPartnerTransactionWithId(@PathVariable("id") Long id){
        LandTransactionDto partnerWithTransactionsDto=landService.getPartnerTransactionWithId(id);
        return ResponseEntity.ok(partnerWithTransactionsDto);
    }

    @DeleteMapping("/partner/transaction/{transactionId}")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<String> deletePartnerTransaction(@PathVariable("transactionId") Long transactionId) {
        landService.deleteSinglePartnerTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully.");
    }
    @GetMapping("/land/{id}/All-Partner-with-Purcher")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<LandTransactionsResponseDto> getLandTransactions(@PathVariable("id") Long id) {
        List<PartnerWithTransactionsDto> partners = landService.getPartnerTransactions(id);
        PurchaserWithTransactionsDto purchaser = landService.getPurchaserTransaction(id);
        LandTransactionsResponseDto response = new LandTransactionsResponseDto();
        response.setPartners(partners);
        response.setPurchaser(purchaser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{landId}/partners")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<Land> addPartnerToLand(@PathVariable("landId") Long landId, @RequestBody PartnerDto partnerDto) {
        Land updatedLand = landService.addPartnerToLand(landId, partnerDto);
        return ResponseEntity.ok(updatedLand);
    }

    @GetMapping("/partner/{id}/transactions")
    @PreAuthorize("hasAnyRole('Admin','SubAdmin')")
    public ResponseEntity<PartnerWithTransactionsDto> getPartnerWithTransactions(@PathVariable("id") Long id) {
        PartnerWithTransactionsDto partner = landService.getSinglePartnerTransactions(id);
        return ResponseEntity.ok(partner);
    }
}
