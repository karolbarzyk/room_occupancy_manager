package com.beusable.hotel.controller;

import com.beusable.hotel.model.OccupancyRequest;
import com.beusable.hotel.model.OccupancyResponse;
import com.beusable.hotel.service.OccupancyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OccupancyControllerTest {

    @Mock
    private OccupancyService occupancyService;

    @InjectMocks
    private OccupancyController occupancyController;

    @Test
    public void testCalculateOccupancy() {
        // GIVEN
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(7);
        request.setEconomyRooms(5);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse expectedResponse = new OccupancyResponse(6, 1054.0, 4, 189.99);

        when(occupancyService.allocateRooms(request)).thenReturn(expectedResponse);

        // WHEN
        ResponseEntity<OccupancyResponse> responseEntity = occupancyController.calculateOccupancy(request);

        // THEN
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        OccupancyResponse actualResponse = responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }
}
