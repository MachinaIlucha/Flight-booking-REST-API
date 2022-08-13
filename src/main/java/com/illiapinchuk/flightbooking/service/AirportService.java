package com.illiapinchuk.flightbooking.service;

import com.illiapinchuk.flightbooking.model.Airport;

import java.util.List;

public interface AirportService {
    Airport saveAirport(Airport airport);
    Airport getAirportById(Long airportId);
    List<Airport> getAllAirports();
}
