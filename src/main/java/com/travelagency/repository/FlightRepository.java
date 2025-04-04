package com.travelagency.repository;

import com.travelagency.model.Airplane;
import com.travelagency.model.City;
import com.travelagency.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByAirplaneOrderByDepartureTime(Airplane airplane);
    
    List<Flight> findByDepartureCityOrArrivalCityAndDepartureTimeBetween(
        City city, City city2, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT f FROM Flight f WHERE f.departureTime >= :startOfDay AND f.departureTime < :endOfDay " +
           "AND (f.departureCity = :city OR f.arrivalCity = :city)")
    List<Flight> findFlightsByDateAndCity(
        @Param("startOfDay") LocalDateTime startOfDay,
        @Param("endOfDay") LocalDateTime endOfDay,
        @Param("city") City city);
} 