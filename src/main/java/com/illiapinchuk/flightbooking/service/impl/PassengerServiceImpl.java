package com.illiapinchuk.flightbooking.service.impl;

import com.illiapinchuk.flightbooking.exception.exceptions.PassengerNotFoundException;
import com.illiapinchuk.flightbooking.model.Booking;
import com.illiapinchuk.flightbooking.model.Passenger;
import com.illiapinchuk.flightbooking.repositories.PassengerRepository;
import com.illiapinchuk.flightbooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(() -> new PassengerNotFoundException(passengerId));
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}
