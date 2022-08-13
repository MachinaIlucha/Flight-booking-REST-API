package com.illiapinchuk.flightbooking.exception.exceptions;

public class AirportNotFoundException extends ResourceNotFoundException{

    public AirportNotFoundException(Long airportId) {
        super("Airport", "airportId", airportId);
    }
}
