package com.illiapinchuk.flightbooking.service;


import com.illiapinchuk.flightbooking.model.Booking;

import java.util.List;

public interface BookingService {
    Booking getBookingById(Long bookingId);

    List<Booking> getAllBookings();

    List<Booking> getAllBookingsByFlight(Long flightId);

    Integer getCountOfBookingsByFlight(Long flightId);

    Booking saveBooking(Booking booking);
}
