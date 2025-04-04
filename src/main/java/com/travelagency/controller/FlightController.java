package com.travelagency.controller;

import com.travelagency.model.Airplane;
import com.travelagency.model.City;
import com.travelagency.model.Flight;
import com.travelagency.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.saveFlight(flight));
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String flightNumber) {
        return ResponseEntity.ok(flightService.getFlightByNumber(flightNumber));
    }

    @PutMapping("/{flightNumber}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String flightNumber, @RequestBody Flight flight) {
        flight.setFlightNumber(flightNumber);
        return ResponseEntity.ok(flightService.saveFlight(flight));
    }

    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-airplane")
    public ResponseEntity<List<Flight>> getFlightsByAirplane(@RequestBody Airplane airplane) {
        return ResponseEntity.ok(flightService.getFlightsByAirplane(airplane));
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<Flight>> getFlightsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestBody City city) {
        return ResponseEntity.ok(flightService.getFlightsBetweenDates(city, start, end));
    }

    @GetMapping("/by-date-and-city")
    public ResponseEntity<List<Flight>> getFlightsByDateAndCity(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody City city) {
        return ResponseEntity.ok(flightService.getFlightsByDateAndCity(date, city));
    }
} 