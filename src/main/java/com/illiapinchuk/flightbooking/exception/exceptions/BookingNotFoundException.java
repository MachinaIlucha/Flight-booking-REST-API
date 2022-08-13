package com.illiapinchuk.flightbooking.exception.exceptions;

public class BookingNotFoundException extends ResourceNotFoundException{

    public BookingNotFoundException(Long bookingId) {
        super("Booking", "flightId", bookingId);
    }
}
