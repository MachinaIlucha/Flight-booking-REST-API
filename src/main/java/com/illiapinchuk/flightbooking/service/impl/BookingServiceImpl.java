package com.illiapinchuk.flightbooking.service.impl;

import com.illiapinchuk.flightbooking.exception.exceptions.BookingNotFoundException;
import com.illiapinchuk.flightbooking.model.Booking;
import com.illiapinchuk.flightbooking.repositories.BookingRepository;
import com.illiapinchuk.flightbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getAllBookingsByFlight(Long flightId) {
        return bookingRepository.getBookingsByFlight(flightId);
    }

    @Override
    public Integer getCountOfBookingsByFlight(Long flightId) {
        return bookingRepository.getCountOfBookingsByFlight(flightId);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
}
