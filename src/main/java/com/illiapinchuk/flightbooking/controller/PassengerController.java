package com.illiapinchuk.flightbooking.controller;

import com.illiapinchuk.flightbooking.model.Passenger;
import com.illiapinchuk.flightbooking.service.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("passengers")
public class PassengerController {
    private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);
    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public @ResponseBody List<Passenger> getAllPassengers() {
        logger.info("Getting all Passengers");
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{passengerId}")
    public @ResponseBody Passenger getPassengerById(@PathVariable("passengerId") Long passengerId) {
        logger.info("Finding Passenger with id: " + passengerId);
        return passengerService.getPassengerById(passengerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Passenger savePassenger(@RequestBody Passenger passenger) {
        logger.info("Saving passenger: " + passenger.toString());
        return passengerService.save(passenger);
    }
}
