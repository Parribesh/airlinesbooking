package com.synergisticit.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SpringJtaSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.RoleTypes;
import com.synergisticit.model.SearchModel;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FlightController {
	
	@Autowired FlightService flightService;
	@Autowired AirlinesService airlinesService;
	@Autowired AirportService airportService;
	@Autowired HttpServletRequest request;
	
	@GetMapping({"/api/flight/flightForm","/", "/home"} )
	public ModelAndView getFlightForm(@ModelAttribute Flight flight, @ModelAttribute SearchModel searchModel, BindingResult br) {
		ModelAndView mv = new ModelAndView("flightForm");
		request.getSession().setAttribute("sess_resv", null);
		mv.addObject("operatingAirlines", airlinesService.getAllAirlines());
		mv.addObject("flight", flight);
		mv.addObject("cities", airportService.getAllAirports().stream().map(e ->  e.getAirportCity()).toList());
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("today", LocalDate.now());
		mv.addObject("message", "Please Search for Destinations to See Availabel Flights!");
		Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().stream().map(e -> e.getAuthority()).toList().contains(RoleTypes.ADMIN.name())) {
		mv.addObject("flights", flightService.getAllFlights());
		}else {
			mv.addObject("flights", flightService.searchFlights(searchModel));
		}
			return mv;
		}
	
	@PostMapping("/api/flight/createFlight")
	public ModelAndView createFlight(@Validated @ModelAttribute Flight flight, @ModelAttribute SearchModel searchModel,  BindingResult br) {
		ModelAndView mv = new ModelAndView("flightForm");
		if(!br.hasErrors()) {
			flightService.createFlight(flight);	
			}
		
		mv.addObject("flight", new Flight());
		mv.addObject("message", "success");
		mv.addObject("today", LocalDate.now());
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("flights", flightService.getAllFlights());	
		mv.addObject("operatingAirlines", airlinesService.getAllAirlines());
		return mv;
	}
	
	@PostMapping("/api/flight/searchFlight")
	public ModelAndView searchFlight(@ModelAttribute Flight flight, @ModelAttribute SearchModel searchModel) {
		ModelAndView mv = new ModelAndView("flightForm");
		List<Flight> flights = flightService.searchFlights(searchModel);
		System.out.println("flights: "+ flights);
		request.getSession().setAttribute("totalPassengers", searchModel.getPassengers());
		request.getSession().setAttribute("currentPassenger", 1);
		mv.addObject("message", "success");
		mv.addObject("cities", airportService.getAllAirports().stream().map(e ->  e.getAirportCity()).toList());
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("today", LocalDate.now());
		var searchResult = flightService.searchFlights(searchModel);
		mv.addObject("flights", searchResult);
		if(searchResult.size() == 0) {
			mv.addObject("message", "No Flights Found For Your Destination. Sorry!! For the Invonvinience ");
		}
		mv.addObject("operatingAirlines", airlinesService.getAllAirlines());
		return mv;
	}
	
	@GetMapping("/api/flight/updateFlight/{flightId}")
	public ModelAndView updateFlight(@PathVariable Long flightId,  @ModelAttribute Flight flight,  @ModelAttribute SearchModel searchModel) {
		ModelAndView mv = new ModelAndView("flightForm");
		Flight fl = flightService.getFlightById(flightId);
		mv.addObject("operatingAirlines", airlinesService.getAllAirlines());
		mv.addObject("f", fl);
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("cities", airportService.getAllAirports().stream().map(e ->  e.getAirportCity()).toList());
		mv.addObject("flight", flight);
		mv.addObject("showEdit", true);
		mv.addObject("today", LocalDate.now());
		Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().stream().map(e -> e.getAuthority()).toList().contains(RoleTypes.ADMIN.name())) {
		mv.addObject("flights", flightService.getAllFlights());
		
		}else {
			mv.addObject("flights", flightService.searchFlights(searchModel));
		}
		
		return mv;
	}
	
	@GetMapping("/api/flight/reserveFlight/{flightId}")
	public ModelAndView deleteFlight(@PathVariable Long flightId,  @ModelAttribute Flight flight) {
		ModelAndView mv = new ModelAndView("flightForm");
		Flight fl = flightService.getFlightById(flightId);
		flightService.deleteFlight(fl);
		mv.addObject("flight", flight);
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("flights", flightService.getAllFlights());
		mv.addObject("operatingAirlines", airlinesService.getAllAirlines());
		return mv;
	}
	
	@GetMapping("/api/flight/searchFlight/{flightId}")
	@ResponseBody
	public Flight searchFlight(@PathVariable Long flightId) {
		
		return flightService.getFlightById(flightId);
	}
	
	
	
	
	
	
	
}
