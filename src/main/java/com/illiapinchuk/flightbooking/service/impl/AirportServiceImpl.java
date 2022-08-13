package com.illiapinchuk.flightbooking.service.impl;

import com.illiapinchuk.flightbooking.exception.exceptions.AirportNotFoundException;
import com.illiapinchuk.flightbooking.model.Airport;
import com.illiapinchuk.flightbooking.repositories.AirportRepository;
import com.illiapinchuk.flightbooking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport getAirportById(Long airportId) {
        return airportRepository.findById(airportId).orElseThrow(() -> new AirportNotFoundException(airportId));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}
