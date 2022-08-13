package com.illiapinchuk.flightbooking.controller;

import com.illiapinchuk.flightbooking.model.Airport;
import com.illiapinchuk.flightbooking.service.AirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airports")
public class AirportController {
    private static final Logger logger = LoggerFactory.getLogger(AirportController.class);
    @Autowired
    private AirportService airportService;

    @GetMapping
    public @ResponseBody List<Airport> getAllAirports() {
        logger.info("Getting all Airports");
        return airportService.getAllAirports();
    }

    @GetMapping("/{airportId}")
    public @ResponseBody Airport getAirportById(@PathVariable("airportId") Long airportId) {
        logger.info("Finding Airport with id: " + airportId);
        return airportService.getAirportById(airportId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Airport saveAirport(@RequestBody Airport airport) {
        logger.info("Saving airport: " + airport.toString());
        return airportService.saveAirport(airport);
    }
}
