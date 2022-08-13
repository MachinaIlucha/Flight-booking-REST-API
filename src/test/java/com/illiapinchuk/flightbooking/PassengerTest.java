package com.illiapinchuk.flightbooking;

import com.illiapinchuk.flightbooking.model.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class PassengerTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(PassengerTest.class);

    @Test
    public void getPassengersList() throws Exception {
        logger.info("Test started - getPassengersList");
        String url = "/passengers";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Passenger[] passengersList = super.mapFromJson(content, Passenger[].class);
        assertTrue(passengersList.length > 0);
        logger.info("Test ended - getPassengersList");
    }

    @Test
    public void getPassengerById() throws Exception {
        logger.info("Test started - getPassengerById");
        String url = "/passengers/4";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Passenger returnedPassengers = super.mapFromJson(content, Passenger.class);
        assertNotNull(returnedPassengers);
        logger.info("\n" + returnedPassengers + "\n" + "Bookings:" + returnedPassengers.getBookings());
        logger.info("Test ended - getPassengerById");
    }

    @Test
    public void createPassenger() throws Exception {
        logger.info("Test started - createPassenger");
        String url = "/passengers";
        Passenger passenger = new Passenger(6L, "firstName", "lastName", "email", null);
        String inputJson = super.mapToJson(passenger);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Passenger returnedPassenger = super.mapFromJson(content, Passenger.class);
        assertEquals(returnedPassenger, passenger);
        logger.info("Test ended - createPassenger");
    }
}
