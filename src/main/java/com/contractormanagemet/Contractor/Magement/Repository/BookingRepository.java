package com.contractormanagemet.Contractor.Magement.Repository;

import com.contractormanagemet.Contractor.Magement.Entity.Booking;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.AvailabilityStatus;
import com.contractormanagemet.Contractor.Magement.Entity.enumeration.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByBookingStatusAndResidency_AvailabilityStatus(BookingStatus bookingStatus, AvailabilityStatus availabilityStatus);

    Booking findBycustomer_id(Long id);
}
