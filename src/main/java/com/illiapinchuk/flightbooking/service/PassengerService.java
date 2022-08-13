package com.illiapinchuk.flightbooking.service;

import com.illiapinchuk.flightbooking.model.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger getPassengerById(Long passengerId);
    List<Passenger> getAllPassengers();
    Passenger save(Passenger passenger);
}
