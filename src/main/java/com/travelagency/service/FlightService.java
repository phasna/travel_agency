package com.travelagency.service;

import com.travelagency.model.Airplane;
import com.travelagency.model.City;
import com.travelagency.model.Flight;
import com.travelagency.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findById(flightNumber)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public void deleteFlight(String flightNumber) {
        flightRepository.deleteById(flightNumber);
    }

    public List<Flight> getFlightsByAirplane(Airplane airplane) {
        return flightRepository.findByAirplaneOrderByDepartureTime(airplane);
    }

    public List<Flight> getFlightsBetweenDates(City city, LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByDepartureCityOrArrivalCityAndDepartureTimeBetween(city, city, start, end);
    }

    public List<Flight> getFlightsByDateAndCity(LocalDate date, City city) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return flightRepository.findFlightsByDateAndCity(startOfDay, endOfDay, city);
    }
} 