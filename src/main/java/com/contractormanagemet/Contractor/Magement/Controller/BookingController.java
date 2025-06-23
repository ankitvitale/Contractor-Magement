package com.contractormanagemet.Contractor.Magement.Controller;

import com.contractormanagemet.Contractor.Magement.DTO.BookingDto.*;
import com.contractormanagemet.Contractor.Magement.Entity.Booking;
import com.contractormanagemet.Contractor.Magement.Entity.BookingInstallment;
import com.contractormanagemet.Contractor.Magement.Entity.Customer;
import com.contractormanagemet.Contractor.Magement.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Booking> createflatbooking(@RequestBody BookingRequestDto bookingDto){
        Booking flatbooking=bookingService.createFlatBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(flatbooking);

    }

    @PostMapping("/{id}/addInstallment")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<BookingPaymentDto> addinstallment(
            @PathVariable("id") Long id,
            @RequestBody List<BookingInstallmentDTO> bookingInstallmentDtos) {
        BookingPaymentDto addInstallment = bookingService.addInstallment(id, bookingInstallmentDtos);
        return ResponseEntity.ok(addInstallment);
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/BookingSummary/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<BookingSummaryDTO> getBookingSummary(@PathVariable("id") Long id) {
        BookingSummaryDTO bookingSummary = bookingService.getBookingSummary(id);
        return ResponseEntity.ok(bookingSummary);
    }

    @GetMapping("/bookings/complete")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Booking>> getAllCompleteBookings() {
        List<Booking> completeBookings = bookingService.getAllCompleteBookings();
        return ResponseEntity.ok(completeBookings);
    }

    @GetMapping("/booking/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") Long id){
        Booking booking=bookingService.getBookingByID(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/updateBooking/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Booking> updateFlatBooking(@PathVariable("id") Long id, @RequestBody BookingRequestDto bookingDto) {
        Booking updatedBooking = bookingService.updateFlatBooking(id, bookingDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
    }
    @GetMapping("/getBookingCoustomerId/{id}")
    @PreAuthorize("hasRole('Admin')")
    public  ResponseEntity<Booking> getBookingByCoustomerId(@PathVariable("id") Long id){
        Booking getBooking=bookingService.getBookingByCoustomerId(id);
        return ResponseEntity.ok(getBooking);
    }

    @PutMapping("/cancelBooking/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Booking> cancelFlatBooking(@PathVariable("id") Long id) {
        Booking cancelledBooking = bookingService.cancelFlatBooking(id);
        return ResponseEntity.ok(cancelledBooking);
    }

    @PutMapping("/updateBookingInstallment/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<BookingInstallmentResponseDTO> updateBookingInstallment(
            @PathVariable("id") Long id,
            @RequestBody BookingInstallmentDTO dto) {
        BookingInstallmentResponseDTO response = bookingService.updateBookingInstallment(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteBookingInstallment/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteBookingInstallment(@PathVariable("id") Long id){
        bookingService.deleteBookingInstallment(id);
        return ResponseEntity.status(HttpStatus.OK).body("BookingInstallment with ID " + id + " has been deleted successfully");

    }
    @GetMapping("/getBookingInstallmentById/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<BookingInstallmentDTO> getBookingInstallmentById(@PathVariable("id") Long id){
        BookingInstallmentDTO bookingInstallment= bookingService.getBookingInstallmentById(id);
        return ResponseEntity.ok(bookingInstallment);
    }

    @PostMapping("/addLoanDetails/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Customer> addBankDetails(@PathVariable("id") Long id, @RequestBody LoanDto loanDto){
        Customer customer=bookingService.addBankDetails(id,loanDto);
        return ResponseEntity.ok(customer);
    }

}
