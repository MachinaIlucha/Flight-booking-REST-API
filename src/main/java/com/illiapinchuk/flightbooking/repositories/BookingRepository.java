package com.illiapinchuk.flightbooking.repositories;

import com.illiapinchuk.flightbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select b from Booking b where b.flight.id = :flightId")
    List<Booking> getBookingsByFlight(Long flightId);

    @Query("select count(b) from Booking b where b.flight.id = :flightId")
    Integer getCountOfBookingsByFlight(Long flightId);
}
