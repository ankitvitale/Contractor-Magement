package com.contractormanagemet.Contractor.Magement.Service;

import com.contractormanagemet.Contractor.Magement.DTO.*;
import com.contractormanagemet.Contractor.Magement.DTO.RequestDTO.LandRequestDTO;
import com.contractormanagemet.Contractor.Magement.Entity.*;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.TransactionChange;
import com.contractormanagemet.Contractor.Magement.Exception.ResourceNotFoundException;
import com.contractormanagemet.Contractor.Magement.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LandService {
    @Autowired
    private LandRepository landRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private LandTransactionRepository landTransactionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Land createLand(LandRequestDTO landRequestDTO) {

        if (landRequestDTO.getAddress() == null) {
            throw new IllegalArgumentException("Address information is required");
        }
        if (landRequestDTO.getPurchaser() == null) {
            throw new IllegalArgumentException("Purchaser information is required");
        }

        // Convert Address DTO to Entity
        Address address = new Address();
        address.setLandmark(landRequestDTO.getAddress().getLandmark());
        address.setPincode(landRequestDTO.getAddress().getPincode());
        address.setCity(landRequestDTO.getAddress().getCity());
        address.setCountry(landRequestDTO.getAddress().getCountry());
        address.setState(landRequestDTO.getAddress().getState());
        address.setMuza(landRequestDTO.getAddress().getMuza());
        address.setKhno(landRequestDTO.getAddress().getKhno());
        address.setPlotno(landRequestDTO.getAddress().getPlotno());
        address.setPhno(landRequestDTO.getAddress().getPhno());

        // Convert Purchaser DTO to Entity
        PurchaserDto purchaserDto = landRequestDTO.getPurchaser();
        Person purchaser = new Person();
        purchaser.setName(purchaserDto.getName());
        purchaser.setAddress(purchaserDto.getAddress());
        purchaser.setPhoneNumber(purchaserDto.getPhoneNumber());
        purchaser.setAadharNumber(purchaserDto.getAadharNumber());
        personRepository.save(purchaser);

        // Convert Owner DTO to Entity
        OwnerDto ownerDto = landRequestDTO.getOwner();
        Person owner = new Person();
        owner.setName(ownerDto.getName());
        owner.setAddress(ownerDto.getAddress());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        owner.setAadharNumber(ownerDto.getAadharNumber());
        personRepository.save(owner);

        // Save Address before assigning to Land
        addressRepository.save(address);

        // Calculate sold amount safely
        double totalAmount = landRequestDTO.getTotalAmount() != null ? landRequestDTO.getTotalAmount() : 0;
        double agreementAmount = landRequestDTO.getAgreementAmount() != null ? landRequestDTO.getAgreementAmount() : 0;
        double tokenAmount = landRequestDTO.getTokenAmount() != null ? landRequestDTO.getTokenAmount() : 0;
        double soldAmount = totalAmount - (agreementAmount + tokenAmount);

        // Create and populate Land entity
        Land land = new Land();
        land.setArea(landRequestDTO.getArea());
        land.setTokenAmount(landRequestDTO.getTokenAmount());
        land.setAgreementAmount(landRequestDTO.getAgreementAmount());
        land.setTotalAmount(landRequestDTO.getTotalAmount());
        land.setLandAddOnDate(landRequestDTO.getLandAddOnDate());
        land.setAddress(address);
        land.setOwner(owner);
        land.setPurchaser(purchaser);

        // First, save the Land entity so we can assign its ID to partners
        Land savedLand = landRepository.save(land);

        // Convert Partners DTOs to Partner Entities
        Set<Partner> partners = new HashSet<>();
        if (landRequestDTO.getPartners() != null) {
            partners = landRequestDTO.getPartners().stream()
                    .map(partnerDto -> {
                        Partner partner = new Partner();
                        partner.setName(partnerDto.getName());
                        partner.setCity(partnerDto.getCity());
                        partner.setPhoneNumber(partnerDto.getPhoneNumber());
                        partner.setPaymentDate(LocalDate.parse(partnerDto.getPaymentDate()));
                        partner.setLand(savedLand); // Assign land before saving
                        return partner;
                    })
                    .collect(Collectors.toSet());

            partnerRepository.saveAll(partners);
        }

        // Assign saved partners to land and save again
        savedLand.setPartners(partners);
        landRepository.save(savedLand);

        return savedLand;
    }


    public List<Land> getAllLands() {
        return landRepository.findAll();
    }

    public Land getLandById(Long id) {
        return landRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Land not found with id: " + id));
    }

    public Land updateLand(Long landId, LandRequestDTO landRequestDto) {
//logger.info("Received request to update land with ID: {}", landId);

        Land existingLand = landRepository.findById(landId)
                .orElseThrow(() -> new EntityNotFoundException("Land not found with ID: " + landId));

        if (landRequestDto.getAddress() == null) {
            throw new IllegalArgumentException("Address information is required.");
        }
        if (landRequestDto.getPurchaser() == null) {
            throw new IllegalArgumentException("Purchaser information is required.");
        }

        // Update Address
        Address address = existingLand.getAddress();
        address.setLandmark(landRequestDto.getAddress().getLandmark());
        address.setPincode(landRequestDto.getAddress().getPincode());
        address.setCity(landRequestDto.getAddress().getCity());
        address.setCountry(landRequestDto.getAddress().getCountry());
        address.setState(landRequestDto.getAddress().getState());
        address.setMuza(landRequestDto.getAddress().getMuza());
        address.setKhno(landRequestDto.getAddress().getKhno());
        address.setPlotno(landRequestDto.getAddress().getPlotno());
        address.setPhno(landRequestDto.getAddress().getPhno());
        addressRepository.save(address);

        // Update Purchaser
        Person purchaser = existingLand.getPurchaser();
        PurchaserDto purchaserDto = landRequestDto.getPurchaser();
        purchaser.setName(purchaserDto.getName());
        purchaser.setAddress(purchaserDto.getAddress());
        purchaser.setPhoneNumber(purchaserDto.getPhoneNumber());
        purchaser.setAadharNumber(purchaserDto.getAadharNumber());
        personRepository.save(purchaser);

        // Update Owner
        Person owner = existingLand.getOwner();
        OwnerDto ownerDto = landRequestDto.getOwner();
        owner.setName(ownerDto.getName());
        owner.setAddress(ownerDto.getAddress());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        owner.setAadharNumber(ownerDto.getAadharNumber());
        personRepository.save(owner);

        // Update Partners
        if (landRequestDto.getPartners() != null) {
            partnerRepository.deleteAll(existingLand.getPartners());
            Set<Partner> updatedPartners = landRequestDto.getPartners().stream()
                    .map(partnerDto -> {
                        Partner partner = new Partner();
                        partner.setName(partnerDto.getName());
                        partner.setCity(partnerDto.getCity());
                        partner.setPhoneNumber(partnerDto.getPhoneNumber());

                        if (partnerDto.getPaymentDate() != null && !partnerDto.getPaymentDate().isEmpty()) {
                            partner.setPaymentDate(LocalDate.parse(partnerDto.getPaymentDate()));
                        } else {
                            partner.setPaymentDate(null); // Handle missing date gracefully
                        }

                        return partner;
                    })
                    .collect(Collectors.toSet());
            partnerRepository.saveAll(updatedPartners);
            existingLand.setPartners(updatedPartners);
        }

        // Calculate sold amount safely
        double totalAmount = landRequestDto.getTotalAmount() != null ? landRequestDto.getTotalAmount() : 0;
        double agreementAmount = landRequestDto.getAgreementAmount() != null ? landRequestDto.getAgreementAmount() : 0;
        double tokenAmount = landRequestDto.getTokenAmount() != null ? landRequestDto.getTokenAmount() : 0;
        double soldAmount = totalAmount - (agreementAmount + tokenAmount);

        // Update Land entity
        existingLand.setArea(landRequestDto.getArea());
        existingLand.setTokenAmount(landRequestDto.getTokenAmount());
        existingLand.setAgreementAmount(landRequestDto.getAgreementAmount());
        existingLand.setTotalAmount(landRequestDto.getTotalAmount());
        existingLand.setLandAddOnDate(landRequestDto.getLandAddOnDate());
        existingLand.setAddress(address);
        existingLand.setOwner(owner);
        existingLand.setPurchaser(purchaser);

        Land updatedLand = landRepository.save(existingLand);

        return updatedLand;
    }

    @Transactional
    public void deleteLand(Long id) {
        // Step 1: Fetch the existing land with all relationships
        Land existingLand = landRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Land not found with ID: " + id));

        // Step 2: Clear and delete partners
        if (existingLand.getPartners() != null && !existingLand.getPartners().isEmpty()) {
            for (Partner partner : existingLand.getPartners()) {
                partner.setLand(null);
            }
            partnerRepository.deleteAll(existingLand.getPartners());
            existingLand.getPartners().clear();
        }

        // Step 3: Handle project reference (avoid foreign key issues)
        if (existingLand.getProject() != null) {
            existingLand.getProject().setLand(null);
        }

        // Step 4: Detach person and address references
        Person purchaser = existingLand.getPurchaser();
        Person owner = existingLand.getOwner();
        Address address = existingLand.getAddress();

        existingLand.setPurchaser(null);
        existingLand.setOwner(null);
        existingLand.setAddress(null);

        // Step 5: Save the land with detached entities
        landRepository.save(existingLand);

        // Step 6: Now delete the land
        landRepository.delete(existingLand);

        // Step 7: Delete detached related entities (optional and only if not reused)
        if (purchaser != null) personRepository.delete(purchaser);
        if (owner != null) personRepository.delete(owner);
        if (address != null) addressRepository.delete(address);
    }




    public LandTransaction addPayment(LandTransactionDto landTransactionDto, Long partnerId) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));
//        // Create LandTransaction
        LandTransaction landTransaction = new LandTransaction();
        landTransaction.setTransactionDate(landTransactionDto.getTransactionDate());
        landTransaction.setTransactionAmount(landTransactionDto.getTransactionAmount());
        landTransaction.setNote(landTransactionDto.getNote());
        landTransaction.setChange(landTransactionDto.getChange());
        landTransaction.setMadeBy(landTransactionDto.getMadeBy());
        landTransaction.setStatus(landTransactionDto.getStatus());
        landTransaction.setPartner(partner);
        //   landTransaction.setPerson(person);

        // Save and return
        return landTransactionRepository.save(landTransaction);
    }

    public LandTransaction addPaymentToPurchaser(LandTransactionDto landTransactionDto, Long purchaserId) {
        Person purchaser = personRepository.findById(purchaserId)
                .orElseThrow(() -> new RuntimeException("Purchaser not found"));

        // Create LandTransaction
        LandTransaction landTransaction = new LandTransaction();
        landTransaction.setTransactionDate(landTransactionDto.getTransactionDate());
        landTransaction.setTransactionAmount(landTransactionDto.getTransactionAmount());
        landTransaction.setNote(landTransactionDto.getNote());
        landTransaction.setChange(landTransactionDto.getChange());
        landTransaction.setMadeBy(landTransactionDto.getMadeBy());
        landTransaction.setStatus(landTransactionDto.getStatus());
        landTransaction.setPerson(purchaser); // Set the Purchaser

        // Save and return
        return landTransactionRepository.save(landTransaction);
    }

    public List<PartnerWithTransactionsDto> getPartnerTransactions(Long landId) {
        List<LandTransaction> landTransactions = landTransactionRepository.findAllByPartnerLandId(landId);

        Map<Long, PartnerWithTransactionsDto> partnerTransactions = new HashMap<>();
        for (LandTransaction transaction : landTransactions) {
            Partner partner = transaction.getPartner();
            if (!partnerTransactions.containsKey(partner.getId())) {
                PartnerWithTransactionsDto partnerWithTransactionsDto = new PartnerWithTransactionsDto();
                partnerWithTransactionsDto.setId(partner.getId());
                partnerWithTransactionsDto.setName(partner.getName());
                partnerWithTransactionsDto.setCity(partner.getCity());
                partnerWithTransactionsDto.setPhoneNumber(partner.getPhoneNumber());
                partnerWithTransactionsDto.setPaymentDate(partner.getPaymentDate().toString());
                partnerWithTransactionsDto.setLandTransactions(new ArrayList<>());
                partnerWithTransactionsDto.setTotal(0.0); // Initialize the total field

                partnerTransactions.put(partner.getId(), partnerWithTransactionsDto);
            }

            double amountToChange = transaction.getChange() == TransactionChange.DEBIT ? transaction.getTransactionAmount() * -1 : transaction.getTransactionAmount();
            PartnerWithTransactionsDto currentPartnerDto = partnerTransactions.get(partner.getId());

            // Update the amount
            //      currentPartnerDto.setAmount(currentPartnerDto.getAmount() + amountToChange);

            // Add transaction details
            LandTransactionDto txDto = new LandTransactionDto();
            txDto.setId(transaction.getId());
            txDto.setTransactionDate(transaction.getTransactionDate());
            txDto.setTransactionAmount(transaction.getTransactionAmount());
            txDto.setNote(transaction.getNote());
            txDto.setChange(transaction.getChange());
            txDto.setMadeBy(transaction.getMadeBy());
            txDto.setStatus(transaction.getStatus());
            currentPartnerDto.getLandTransactions().add(txDto);

            // Update the total: sum of amount and all transaction amounts
            double transactionTotal = currentPartnerDto.getLandTransactions().stream()
                    .mapToDouble(LandTransactionDto::getTransactionAmount)
                    .sum();
            //    currentPartnerDto.setTotal(currentPartnerDto.getAmount() + transactionTotal);
            currentPartnerDto.setTotal(currentPartnerDto.getTotal() + transactionTotal);
        }

        System.out.println(partnerTransactions);
        return partnerTransactions.values().stream().toList();
    }

    public PurchaserWithTransactionsDto getPurchaserTransaction(Long landId) {
        Land land = landRepository.findById(landId)
                .orElseThrow(() -> new IllegalArgumentException("Land not found"));
        Person purchaser = land.getPurchaser();

        if (purchaser == null) {
            return null; // No purchaser found for the land
        }

        // Fetch all transactions for the purchaser
        List<LandTransaction> transactions = landTransactionRepository.findAllByPerson_Id(purchaser.getId());

        // Initialize DTO and populate fields
        PurchaserWithTransactionsDto dto = new PurchaserWithTransactionsDto();
        dto.setId(purchaser.getId());
        dto.setName(purchaser.getName());
        dto.setPhoneNumber(purchaser.getPhoneNumber());
        dto.setAddress(purchaser.getAddress());
        dto.setAadharNumber(purchaser.getAadharNumber());




        double totalAmount = transactions.stream()
                .map(tx -> tx.getChange() == TransactionChange.DEBIT ? -tx.getTransactionAmount() : tx.getTransactionAmount())
                .reduce(0.0, Double::sum);

        dto.setTotal(totalAmount);

        // Map transactions to DTO
        dto.setLandTransactions(transactions.stream().map(tx -> {
            LandTransactionDto txDto = new LandTransactionDto();
            txDto.setId(tx.getId());
            txDto.setTransactionDate(tx.getTransactionDate());
            txDto.setTransactionAmount(tx.getTransactionAmount());
            txDto.setNote(tx.getNote());
            txDto.setChange(tx.getChange());
            txDto.setMadeBy(tx.getMadeBy());
            txDto.setStatus(tx.getStatus());
            return txDto;
        }).toList());

        return dto;
    }

    public List<PartnerWithTransactionsDto> getPartnersByLandId(Long landId) {
        System.out.println("Fetching partners and transactions for landId: " + landId);

        // Fetch all partners for the given landId
        List<Partner> partners = partnerRepository.findAllByLandId(landId);
        System.out.println("Total partners found: " + partners.size());

        // Fetch all transactions related to this landId
        List<LandTransaction> landTransactions = landTransactionRepository.findAllByPartnerLandId(landId);
        System.out.println("Total transactions found: " + landTransactions.size());

        Map<Long, PartnerWithTransactionsDto> partnerTransactions = new HashMap<>();

        // Initialize DTOs for all partners
        for (Partner partner : partners) {
            partnerTransactions.computeIfAbsent(partner.getId(), id -> {
                PartnerWithTransactionsDto dto = new PartnerWithTransactionsDto();
                dto.setId(partner.getId());
                dto.setName(partner.getName());
                dto.setCity(partner.getCity());
                dto.setPhoneNumber(partner.getPhoneNumber());
                dto.setPaymentDate(partner.getPaymentDate() != null ? partner.getPaymentDate().toString() : null);
                dto.setTotal(0.0);
                dto.setLandTransactions(new ArrayList<>());
                return dto;
            });
        }

        // Process transactions and map them to the correct partner
        for (LandTransaction transaction : landTransactions) {
            Partner partner = transaction.getPartner();
            if (partner == null) {
                System.out.println("Skipping transaction ID " + transaction.getId() + " because partner is null.");
                continue;
            }

            PartnerWithTransactionsDto dto = partnerTransactions.get(partner.getId());

            double amountToChange = transaction.getChange() == TransactionChange.DEBIT
                    ? -transaction.getTransactionAmount()
                    : transaction.getTransactionAmount();

            dto.setTotal(dto.getTotal() + amountToChange);

            LandTransactionDto txDto = new LandTransactionDto();
            txDto.setId(transaction.getId());
            txDto.setTransactionDate(transaction.getTransactionDate());
            txDto.setTransactionAmount(transaction.getTransactionAmount());
            txDto.setNote(transaction.getNote());
            txDto.setChange(transaction.getChange());
            txDto.setMadeBy(transaction.getMadeBy());
            txDto.setStatus(transaction.getStatus());

            dto.getLandTransactions().add(txDto);
        }

        return new ArrayList<>(partnerTransactions.values());
    }

    public LandTransaction updatePayment(LandTransactionDto landTransactionDto, Long transactionId) {
        LandTransaction landTransaction = landTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        landTransaction.setTransactionDate(landTransactionDto.getTransactionDate());
        landTransaction.setTransactionAmount(landTransactionDto.getTransactionAmount());
        landTransaction.setNote(landTransactionDto.getNote());
        landTransaction.setChange(landTransactionDto.getChange());
        landTransaction.setMadeBy(landTransactionDto.getMadeBy());
        landTransaction.setStatus(landTransactionDto.getStatus());

        return landTransactionRepository.save(landTransaction);
    }

    public LandTransactionDto getPartnerTransactionWithId(Long id) {
        LandTransaction transaction = landTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Land Transaction not found with ID: " + id));

        LandTransactionDto txDto = new LandTransactionDto();
        txDto.setId(transaction.getId());
        txDto.setTransactionDate(transaction.getTransactionDate());
        txDto.setTransactionAmount(transaction.getTransactionAmount());
        txDto.setNote(transaction.getNote());
        txDto.setChange(transaction.getChange());
        txDto.setMadeBy(transaction.getMadeBy());
        txDto.setStatus(transaction.getStatus());

        return txDto;

    }

    public void deleteSinglePartnerTransaction(Long transactionId) {
        // Fetch the transaction by ID
        LandTransaction transaction = landTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + transactionId));

        // Delete the transaction
        landTransactionRepository.delete(transaction);
    }

    public Land addPartnerToLand(Long landId, PartnerDto partnerDto) {

        Land land = landRepository.findById(landId)
                .orElseThrow(() -> new EntityNotFoundException("Land not found with ID: " + landId));

        Partner partner = new Partner();
        partner.setName(partnerDto.getName());
        partner.setCity(partnerDto.getCity());
        partner.setPhoneNumber(partnerDto.getPhoneNumber());
        //  partner.setAmount(partnerDto.getAmount());
//       partner.setPaymentDate(LocalDate.parse(partnerDto.getPaymentDate()));
        partner.setLand(land);

        partnerRepository.save(partner);

        land.getPartners().add(partner);
        landRepository.save(land);

        return land;
    }

    public PartnerWithTransactionsDto getSinglePartnerTransactions(Long partnerId) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new IllegalArgumentException("Partner not found with id: " + partnerId));

        List<LandTransaction> transactions = landTransactionRepository.findAllByPartnerId(partnerId);

        PartnerWithTransactionsDto dto = new PartnerWithTransactionsDto();
        dto.setId(partner.getId());
        dto.setName(partner.getName());
        dto.setCity(partner.getCity());
        dto.setPhoneNumber(partner.getPhoneNumber());
        dto.setPaymentDate(partner.getPaymentDate() != null ? partner.getPaymentDate().toString() : null);

        double totalAmount = transactions.stream()
                .mapToDouble(tx -> tx.getChange() == TransactionChange.DEBIT
                        ? -tx.getTransactionAmount()
                        : tx.getTransactionAmount())
                .sum();

        dto.setTotal(totalAmount);

        dto.setLandTransactions(transactions.stream().map(tx -> {
            LandTransactionDto txDto = new LandTransactionDto();
            txDto.setId(tx.getId());
            txDto.setTransactionDate(tx.getTransactionDate());
            txDto.setTransactionAmount(tx.getTransactionAmount());
            txDto.setNote(tx.getNote());
            txDto.setChange(tx.getChange());
            txDto.setMadeBy(tx.getMadeBy());
            txDto.setStatus(tx.getStatus());
            return txDto;
        }).toList());

        return dto;
    }
}
