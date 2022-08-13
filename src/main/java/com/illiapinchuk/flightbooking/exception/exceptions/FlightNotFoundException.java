package com.illiapinchuk.flightbooking.exception.exceptions;

public class FlightNotFoundException extends ResourceNotFoundException{
    public FlightNotFoundException(Long flightId) {
        super("Flight", "flightId", flightId);
    }
}
