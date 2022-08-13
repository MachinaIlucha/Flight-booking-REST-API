package com.illiapinchuk.flightbooking.service;

import com.illiapinchuk.flightbooking.model.Flight;

import java.util.List;

public interface FlightService {
    Flight saveFlight(Flight flight);
    Flight getFlightById(Long flightId);
    List<Flight> getAllFlights();
}
