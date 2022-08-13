package com.illiapinchuk.flightbooking.repositories;

import com.illiapinchuk.flightbooking.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
