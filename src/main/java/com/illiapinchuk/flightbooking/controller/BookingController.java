package com.illiapinchuk.flightbooking.controller;

import com.illiapinchuk.flightbooking.model.Booking;
import com.illiapinchuk.flightbooking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public @ResponseBody Booking getBookingById(@PathVariable Long bookingId) {
        logger.info("Finding booking by id: " + bookingId);
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping
    public @ResponseBody List<Booking> getAllBookings() {
        logger.info("Getting all bookings");
        return bookingService.getAllBookings();
    }

    @GetMapping("/flight/{flightId}")
    public @ResponseBody List<Booking> getAllBookingsByFlight(@PathVariable Long flightId) {
        logger.info("Getting all bookings by flight");
        return bookingService.getAllBookingsByFlight(flightId);
    }

    @GetMapping("/flight/{flightId}/count")
    public @ResponseBody Integer getCountOfBookingsByFlight(@PathVariable Long flightId) {
        logger.info("Getting count of bookings by flight");
        return bookingService.getCountOfBookingsByFlight(flightId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Booking getFlightById(@RequestBody Booking booking) {
        logger.info("Saving Booking: " + booking.toString());
        return bookingService.saveBooking(booking);
    }
}
