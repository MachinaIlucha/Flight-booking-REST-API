package com.illiapinchuk.flightbooking.exception.exceptions;

public class PassengerNotFoundException extends ResourceNotFoundException{
    public PassengerNotFoundException(Long passengerId) {
        super("Passenger", "passengerId", passengerId);
    }
}
