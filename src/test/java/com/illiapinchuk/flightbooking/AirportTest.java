package com.illiapinchuk.flightbooking;

import com.illiapinchuk.flightbooking.model.Airport;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest extends AbstractTest{
    private static final Logger logger = LoggerFactory.getLogger(FlightTest.class);

    @Test
    public void createAirport() throws Exception {
        logger.info("Test started - createAirport");
        String url = "/airports";
        Airport airport = new Airport(2L, "Test", "London");
        String inputJson = super.mapToJson(airport);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        Airport returnedAirport = super.mapFromJson(content, Airport.class);
        assertEquals(returnedAirport, airport);
        logger.info("Test ended - createAirport");
    }

    @Test
    public void getAirportsList() throws Exception {
        logger.info("Test started - getAirportsList");
        String url = "/airports";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Airport[] airportList = super.mapFromJson(content, Airport[].class);
        assertTrue(airportList.length > 0);
        logger.info("Test ended - getAirportsList");
    }

    @Test
    public void getAirportById() throws Exception {
        logger.info("Test started - getAirportById");
        String url = "/airports/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Airport returnedAirport = super.mapFromJson(content, Airport.class);
        assertNotNull(returnedAirport);
        logger.info("Test ended - getAirportById");
    }
}
