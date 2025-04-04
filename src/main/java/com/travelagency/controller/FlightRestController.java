package com.travelagency.controller;

import com.travelagency.model.Airplane;
import com.travelagency.model.City;
import com.travelagency.model.Flight;
import com.travelagency.service.FlightService;
import com.travelagency.repository.AirplaneRepository;
import com.travelagency.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightRestController {
    private final FlightService flightService;
    private final AirplaneRepository airplaneRepository;
    private final CityRepository cityRepository;

    @GetMapping("/by-airplane/{airplaneId}")
    public ResponseEntity<List<Flight>> getFlightsByAirplane(@PathVariable Integer airplaneId) {
        Airplane airplane = airplaneRepository.findById(airplaneId)
                .orElseThrow(() -> new RuntimeException("Airplane not found"));
        return ResponseEntity.ok(flightService.getFlightsByAirplane(airplane));
    }

    @GetMapping("/by-date-and-city/{cityId}")
    public ResponseEntity<List<Flight>> getFlightsByDateAndCity(
            @PathVariable Integer cityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));
        return ResponseEntity.ok(flightService.getFlightsByDateAndCity(date, city));
    }
} 