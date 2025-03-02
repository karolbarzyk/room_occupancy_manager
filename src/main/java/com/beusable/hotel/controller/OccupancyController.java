package com.beusable.hotel.controller;


import com.beusable.hotel.model.OccupancyRequest;
import com.beusable.hotel.model.OccupancyResponse;
import com.beusable.hotel.service.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

    private final OccupancyService occupancyService;

    @Autowired
    public OccupancyController(OccupancyService occupancyService) {
        this.occupancyService = occupancyService;
    }

    @PostMapping
    public ResponseEntity<OccupancyResponse> calculateOccupancy(@RequestBody OccupancyRequest request) {
        OccupancyResponse response = occupancyService.allocateRooms(request);
        return ResponseEntity.ok(response);
    }
}
