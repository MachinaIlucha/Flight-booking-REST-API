package com.illiapinchuk.flightbooking;

import com.illiapinchuk.flightbooking.model.Booking;
import com.illiapinchuk.flightbooking.model.Flight;
import com.illiapinchuk.flightbooking.model.Passenger;
import com.illiapinchuk.flightbooking.service.FlightService;
import com.illiapinchuk.flightbooking.service.PassengerService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest extends AbstractTest{
    private static final Logger logger = LoggerFactory.getLogger(BookingTest.class);

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private FlightService flightService;

    @Test
    public void getBookingsList() throws Exception {
        logger.info("Test started - getBookingsList");
        String url = "/bookings";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Booking[] bookingList = super.mapFromJson(content, Booking[].class);
        assertTrue(bookingList.length > 0);
        logger.info("Test ended - getBookingsList");
    }

    @Test
    public void getBookingById() throws Exception {
        logger.info("Test started - getBookingById");
        String url = "/bookings/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Booking returnedBooking = super.mapFromJson(content, Booking.class);
        assertNotNull(returnedBooking);
        logger.info("Test ended - getBookingById");
    }

    @Test
    public void getBookingByFlight() throws Exception {
        logger.info("Test started - getBookingByFlight");
        String url = "/bookings/flight/6";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Booking[] bookingList = super.mapFromJson(content, Booking[].class);
        assertTrue(bookingList.length > 0);
        logger.info("Test ended - getBookingByFlight");
    }

    @Test
    public void getCountOfBookingByFlight() throws Exception {
        logger.info("Test started - getBookingByFlight");
        String url = "/bookings/flight/6/count";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "1");
        logger.info("Test ended - getBookingByFlight");
    }

    @Test
    public void createBooking() throws Exception {
        logger.info("Test started - createBooking");
        String url = "/bookings";

        Passenger passenger = passengerService.getPassengerById(4L);
        Flight flight = flightService.getFlightById(6L);
        Booking booking = new Booking(1L, passenger, flight);
        String inputJson = super.mapToJson(booking);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Booking returnedBooking = super.mapFromJson(content, Booking.class);
        assertEquals(returnedBooking, booking);
        logger.info("Test ended - createBooking");
    }
}
