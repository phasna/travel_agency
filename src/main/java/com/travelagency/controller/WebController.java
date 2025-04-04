package com.travelagency.controller;

import com.travelagency.model.Flight;
import com.travelagency.service.FlightService;
import com.travelagency.repository.AirplaneRepository;
import com.travelagency.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final FlightService flightService;
    private final CityRepository cityRepository;
    private final AirplaneRepository airplaneRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "index";
    }

    @GetMapping("/flights")
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights/list";
    }

    @GetMapping("/flights/new")
    public String showNewFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("airplanes", airplaneRepository.findAll());
        return "flights/form";
    }

    @PostMapping("/flights/save")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }

    @GetMapping("/flights/edit/{flightNumber}")
    public String showEditFlightForm(@PathVariable String flightNumber, Model model) {
        model.addAttribute("flight", flightService.getFlightByNumber(flightNumber));
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("airplanes", airplaneRepository.findAll());
        return "flights/form";
    }

    @GetMapping("/flights/delete/{flightNumber}")
    public String deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return "redirect:/flights";
    }

    @GetMapping("/flights/by-airplane")
    public String listFlightsByAirplane(Model model) {
        model.addAttribute("airplanes", airplaneRepository.findAll());
        return "flights/by-airplane";
    }

    @GetMapping("/flights/by-date-city")
    public String showFlightsByDateAndCity(Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("today", LocalDate.now());
        return "flights/by-date-city";
    }
} 