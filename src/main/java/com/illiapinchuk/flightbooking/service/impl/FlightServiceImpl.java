package com.illiapinchuk.flightbooking.service.impl;

import com.illiapinchuk.flightbooking.exception.exceptions.FlightNotFoundException;
import com.illiapinchuk.flightbooking.model.Flight;
import com.illiapinchuk.flightbooking.repositories.FlightRepository;
import com.illiapinchuk.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
