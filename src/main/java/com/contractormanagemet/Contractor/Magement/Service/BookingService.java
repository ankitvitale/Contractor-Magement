package com.contractormanagemet.Contractor.Magement.Service;


import com.contractormanagemet.Contractor.Magement.DTO.BookingDto.*;
import com.contractormanagemet.Contractor.Magement.DTO.CustomerDto.CustomerRequestDto;
import com.contractormanagemet.Contractor.Magement.Entity.Booking;
import com.contractormanagemet.Contractor.Magement.Entity.BookingInstallment;
import com.contractormanagemet.Contractor.Magement.Entity.Customer;
import com.contractormanagemet.Contractor.Magement.Entity.Residency;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.AvailabilityStatus;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.BookingStatus;
import com.contractormanagemet.Contractor.Magement.Repository.BookingInstallmentRepository;
import com.contractormanagemet.Contractor.Magement.Repository.BookingRepository;
import com.contractormanagemet.Contractor.Magement.Repository.CustomerRepository;
import com.contractormanagemet.Contractor.Magement.Repository.ResidencyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ResidencyRepository residencyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingInstallmentRepository bookingInstallmentRepository;

    public Booking createFlatBooking(BookingRequestDto bookingDto) {
        if (bookingDto == null) {
            throw new IllegalArgumentException("Booking data cannot be null.");
        }

        // Map BookingDto to Booking entity
        Booking booking = new Booking();
        booking.setDealPrice(bookingDto.getDealPrice());
        booking.setTokenAmount(bookingDto.getTokenAmount());
        booking.setAgreementAmount(bookingDto.getAgreementAmount());
        booking.setStampDutyAmount(bookingDto.getStampDutyAmount());
        booking.setRegistrationAmount(bookingDto.getRegistrationAmount());
        booking.setGstAmount(bookingDto.getGstAmount());
        booking.setElectricWaterAmmount(bookingDto.getElectricWaterAmmount());
        booking.setLegalChargesAmmout(bookingDto.getLegalChargesAmmout());
        booking.setBookedOn(bookingDto.getBookedOn());
        booking.setBookingStatus(bookingDto.getBookingStatus());

        if (bookingDto.getCustomerDto() != null) {
            CustomerRequestDto customerDto = bookingDto.getCustomerDto();

            // Check if customer exists, or create a new one if the ID is null
            Customer customer;
            if (customerDto.getId() != null) {
                customer = customerRepository.findById(customerDto.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerDto.getId() + " not found."));
            } else {
                // If ID is not provided, create a new customer
                customer = new Customer();
                customer.setName(customerDto.getName());
                customer.setEmail(customerDto.getEmail());
                customer.setAadharNumber(customerDto.getAadharNumber());
                customer.setPanCard(customerDto.getPanCard());
                customer.setAddress(customerDto.getAddress());
                customer.setPhoneNumber(customerDto.getPhoneNumber());
                customer.setAgentName(customerDto.getAgentName());
                customer.setBrokerage(customerDto.getBrokerage());
                customer.setLoan(customerDto.getLoan());
                customer.setBankName(customerDto.getBankName());
                customer.setLoanAmount(customerDto.getLoanAmount());

                customer = customerRepository.save(customer); // Save the new customer
            }

            booking.setCustomer(customer); // Set the customer on the booking entity
        }

        // Handling Residency entity
        if (bookingDto.getResidencyDto() != null && bookingDto.getResidencyDto().getId() != null) {
            Residency residency = residencyRepository.findById(bookingDto.getResidencyDto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Residency with ID " + bookingDto.getResidencyDto().getId() + " not found."));
            residency.setAvailabilityStatus(AvailabilityStatus.BOOKED);
            booking.setResidency(residency);
        } else {
            throw new IllegalArgumentException("Residency ID is required.");
        }

        // Save the booking to the database
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingSummaryDTO getBookingSummary(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + id + " not found"));

        BookingSummaryDTO dto = new BookingSummaryDTO();
        dto.setCustomerName(booking.getCustomer().getName());
        dto.setResidencyName(booking.getResidency().getName());
        dto.setIdentifier(booking.getResidency().getIdentifier());
        dto.setDealPrice(booking.getDealPrice());
        dto.setTokenAmount(booking.getTokenAmount());
        dto.setAgreementAmount(booking.getAgreementAmount());

        List<BookingInstallmentDTO> installmentDTOs = new ArrayList<>();

        for (BookingInstallment installment : booking.getBookingInstallments()) {
            BookingInstallmentDTO installmentDTO = new BookingInstallmentDTO();
            installmentDTO.setId(installment.getId());
            installmentDTO.setInstallmentDate(installment.getInstallmentDate());
            installmentDTO.setInstallmentAmount(installment.getInstallmentAmount());
            installmentDTO.setInstallmentStatus(installment.getInstallmentStatus());
            installmentDTO.setRemark(installment.getRemark());

            installmentDTOs.add(installmentDTO);
        }

        dto.setBookingInstallments(installmentDTOs);

        // Calculate remaining amount
        double totalInstallmentsPaid = installmentDTOs.stream()
                .mapToDouble(BookingInstallmentDTO::getInstallmentAmount)
                .sum();
        dto.setRemainingAmount(dto.getDealPrice() - dto.getTokenAmount()-dto.getAgreementAmount()-totalInstallmentsPaid);

        return dto;
    }

    public List<Booking> getAllCompleteBookings() {
        return bookingRepository.findByBookingStatusAndResidency_AvailabilityStatus(BookingStatus.COMPLETE, AvailabilityStatus.BOOKED);
    }

    public Booking getBookingByID(Long id) {
        return bookingRepository.findById(id).orElseThrow(()-> new RuntimeException("Booking ID is not present"));
    }

    @Transactional
    public Booking updateFlatBooking(Long bookingId, BookingRequestDto bookingDto) {
        if (bookingDto == null) {
            throw new IllegalArgumentException("Booking data cannot be null.");
        }

        // Find the existing booking by ID
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking with ID " + bookingId + " not found."));

        // Update Booking details
        existingBooking.setDealPrice(bookingDto.getDealPrice());
        existingBooking.setTokenAmount(bookingDto.getTokenAmount());
        existingBooking.setAgreementAmount(bookingDto.getAgreementAmount());
        existingBooking.setStampDutyAmount(bookingDto.getStampDutyAmount());
        existingBooking.setRegistrationAmount(bookingDto.getRegistrationAmount());
        existingBooking.setGstAmount(bookingDto.getGstAmount());
        existingBooking.setElectricWaterAmmount(bookingDto.getElectricWaterAmmount());
        existingBooking.setLegalChargesAmmout(bookingDto.getLegalChargesAmmout());
        existingBooking.setBookedOn(bookingDto.getBookedOn());
        existingBooking.setBookingStatus(bookingDto.getBookingStatus());

        if (bookingDto.getCustomerDto() != null) {
            CustomerRequestDto customerDto = bookingDto.getCustomerDto();
            Customer customer;

            if (customerDto.getId() != null) {
                customer = customerRepository.findById(customerDto.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Customer with ID " + customerDto.getId() + " not found."));
            } else {
                customer = new Customer();
            }

            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setAadharNumber(customerDto.getAadharNumber());
            customer.setPanCard(customerDto.getPanCard());
            customer.setAddress(customerDto.getAddress());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customer.setAgentName(customerDto.getAgentName());
            customer.setBrokerage(customerDto.getBrokerage());

            Customer savedCustomer = customerRepository.save(customer);
            existingBooking.setCustomer(savedCustomer);
        }


        // Save updated booking to the database
        return bookingRepository.save(existingBooking);
    }

    public Booking getBookingByCoustomerId(Long id) {
        return bookingRepository.findBycustomer_id(id);
    }

    public Booking cancelFlatBooking(Long bookingId) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Booking ID cannot be null.");
        }

        // Retrieve the existing booking by ID
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking with ID " + bookingId + " not found."));

        // Update booking status to CANCELLED
        existingBooking.setBookingStatus(BookingStatus.CANCELLED);

        // Update residency status to AVAILABLE if linked residency exists
        Residency residency = existingBooking.getResidency();
        if (residency != null) {
            residency.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            residency.setBooking(null);

            residencyRepository.save(residency);
        }

        // Save the updated booking to the database
        return bookingRepository.save(existingBooking);
    }



    public BookingInstallmentResponseDTO updateBookingInstallment(Long id, BookingInstallmentDTO dto) {
        BookingInstallment bookingInstallment = bookingInstallmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookingInstallment " + id + " Not Found"));

        bookingInstallment.setInstallmentDate(dto.getInstallmentDate());
        bookingInstallment.setInstallmentAmount(dto.getInstallmentAmount());
        bookingInstallment.setRemark(dto.getRemark());
        bookingInstallment.setInstallmentStatus(dto.getInstallmentStatus());

        BookingInstallment updated = bookingInstallmentRepository.save(bookingInstallment);

        // Convert to DTO
        BookingInstallmentResponseDTO response = new BookingInstallmentResponseDTO();
        response.setId(updated.getId());
        response.setInstallmentDate(updated.getInstallmentDate());
        response.setInstallmentAmount(updated.getInstallmentAmount());
        response.setRemark(updated.getRemark());
        response.setInstallmentStatus(updated.getInstallmentStatus());
        response.setBookingId(updated.getBooking().getId());

        return response;
    }


    public void deleteBookingInstallment(Long id) {
        BookingInstallment bookingInstallment=bookingInstallmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("BookingInstallment"+id+"Id Not Found"));
        bookingInstallmentRepository.delete(bookingInstallment);
    }

    public BookingInstallmentDTO getBookingInstallmentById(Long id) {
        BookingInstallment bookingInstallment = bookingInstallmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("This Booking ID is not found"));

        BookingInstallmentDTO dto = new BookingInstallmentDTO();
        dto.setId(bookingInstallment.getId());
        dto.setInstallmentAmount(bookingInstallment.getInstallmentAmount());
        dto.setInstallmentDate(bookingInstallment.getInstallmentDate());
        dto.setInstallmentStatus(bookingInstallment.getInstallmentStatus());
        dto.setRemark(bookingInstallment.getRemark());

        return dto;
    }

    public BookingPaymentDto addInstallment(Long id, List<BookingInstallmentDTO> bookingInstallmentDtos) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking " + id + " is not found"));

        List<BookingInstallment> bookingInstallments = bookingInstallmentDtos.stream().map(dto -> {
            BookingInstallment installment = new BookingInstallment();
            installment.setInstallmentDate(dto.getInstallmentDate());
            installment.setInstallmentAmount(dto.getInstallmentAmount());
            installment.setRemark(dto.getRemark());
            installment.setInstallmentStatus(dto.getInstallmentStatus());
            installment.setBooking(booking);
            return installment;
        }).collect(Collectors.toList());

        booking.getBookingInstallments().addAll(bookingInstallments);
        bookingInstallmentRepository.saveAll(bookingInstallments);

        return new BookingPaymentDto(booking);
    }

    public Customer addBankDetails(Long id, LoanDto loanDto) {
        // Find customer by ID
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            // Update customer details from LoanDto
            customer.setBankName(loanDto.getBankName());
            customer.setLoanAmount(loanDto.getLoanAmount());

            // Save the updated customer back to the database
            return customerRepository.save(customer);

        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }
}
