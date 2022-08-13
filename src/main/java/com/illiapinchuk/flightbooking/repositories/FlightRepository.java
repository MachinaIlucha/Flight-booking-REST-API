package com.illiapinchuk.flightbooking.repositories;

import com.illiapinchuk.flightbooking.model.Airport;
import com.illiapinchuk.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAndDepartureDateGreaterThan(Airport departure, LocalDateTime departureDate);
}
