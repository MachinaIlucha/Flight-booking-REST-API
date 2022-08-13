package com.illiapinchuk.flightbooking;

import com.illiapinchuk.flightbooking.model.Airport;
import com.illiapinchuk.flightbooking.model.Flight;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest extends AbstractTest{
    private static final Logger logger = LoggerFactory.getLogger(FlightTest.class);

    @Test
    public void createFlight() throws Exception {
        logger.info("Test started - createFlight");
        String url = "/flights";
        Airport departure = new Airport(1L, "AeroLondon", "London");
        Airport arrival = new Airport(2L, "AeroKyiv", "Kyiv");
        Flight flight = new Flight(6L, departure, arrival, LocalDateTime.now(), LocalDateTime.now());
        String inputJson = super.mapToJson(flight);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Flight returnedFlight = super.mapFromJson(content, Flight.class);
        assertEquals(returnedFlight, flight);
        logger.info("Test ended - createFlight");
    }

    @Test
    public void getFlightsList() throws Exception {
        logger.info("Test started - getFlightsList");
        String url = "/flights";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Flight[] flightList = super.mapFromJson(content, Flight[].class);
        assertTrue(flightList.length > 0);
        logger.info("Test ended - getFlightsList");
    }

    @Test
    public void getFlightById() throws Exception {
        logger.info("Test started - getFlightById");
        String url = "/flights/3";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Flight returnedFlight = super.mapFromJson(content, Flight.class);
        assertNotNull(returnedFlight);
        logger.info("Test ended - getFlightById");
    }
}
