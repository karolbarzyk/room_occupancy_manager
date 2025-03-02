package com.beusable.hotel.service;

import com.beusable.hotel.model.OccupancyRequest;
import com.beusable.hotel.model.OccupancyResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OccupancyServiceTest {

    private final OccupancyService occupancyService = new OccupancyService();

    @Test
    public void testScenario1() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(3);
        request.setEconomyRooms(3);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse expectedResponse = new OccupancyResponse(3, 738.0, 3, 167.99);
        OccupancyResponse actualResponse = occupancyService.allocateRooms(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testScenario2() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(7);
        request.setEconomyRooms(5);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse expectedResponse = new OccupancyResponse(6, 1054.0, 4, 189.99);
        OccupancyResponse actualResponse = occupancyService.allocateRooms(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testScenario3() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(2);
        request.setEconomyRooms(7);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse expectedResponse = new OccupancyResponse(2, 583.0, 4, 189.99);
        OccupancyResponse actualResponse = occupancyService.allocateRooms(request);
        assertEquals(expectedResponse, actualResponse);
    }
}
