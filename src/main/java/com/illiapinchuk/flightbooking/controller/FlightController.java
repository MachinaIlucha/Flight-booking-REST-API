package com.illiapinchuk.flightbooking.controller;

import com.illiapinchuk.flightbooking.model.Flight;
import com.illiapinchuk.flightbooking.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    @Autowired
    private FlightService flightService;

    @GetMapping
    public @ResponseBody List<Flight> getAllFlights() {
        logger.info("Getting all Flights");
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public @ResponseBody Flight getFlightById(@PathVariable("flightId") Long flightId) {
        logger.info("Finding Flight with id: " + flightId);
        return flightService.getFlightById(flightId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Flight getFlightById(@RequestBody Flight flight) {
        logger.info("Saving Flight: " + flight.toString());
        return flightService.saveFlight(flight);
    }
}
