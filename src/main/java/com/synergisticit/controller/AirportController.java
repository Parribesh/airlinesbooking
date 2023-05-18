package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;

@Controller
public class AirportController {

	@Autowired AirportService airportService;
	@Autowired FlightService flightService;
	
	@GetMapping("/api/airport/airportForm")
	public ModelAndView getAirportForm(@ModelAttribute Airport airport, BindingResult br) {
		ModelAndView mv = new ModelAndView("airportForm");
		mv.addObject("airport", airport);
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("flights", flightService.getAllFlights());
		return mv;
	}
	
	@PostMapping("/api/airport/createAirport")
	public ModelAndView createAirport(@Validated @ModelAttribute Airport airport, BindingResult br) {
		ModelAndView mv = new ModelAndView("airportForm");
		System.out.println("airport.getName():"+ airport.getAirportName());
		if(!br.hasErrors()) {
			airportService.createAirport(airport);		
			}
		
		mv.addObject("airport", airport);
		mv.addObject("message", "success");
		mv.addObject("airports", airportService.getAllAirports());	
		mv.addObject("flights", flightService.getAllFlights());
		return mv;
	}
	
	@GetMapping("/api/airport/updateAirport/{airportId}")
	public ModelAndView updateAirport(@PathVariable Long airportId,  @ModelAttribute Airport airport) {
		ModelAndView mv = new ModelAndView("airportForm");
		Airport ar = airportService.getAirportById(airportId);
		mv.addObject("ap", ar);
		mv.addObject("airport", airport);
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("flights", flightService.getAllFlights());
		return mv;
	}
	
	@GetMapping("/api/airport/deleteAirport/{airportId}")
	public ModelAndView deleteAirport(@PathVariable Long airportId,  @ModelAttribute Airport airport) {
		ModelAndView mv = new ModelAndView("airportForm");
		Airport ar = airportService.getAirportById(airportId);
		airportService.deleteAirport(ar);
		mv.addObject("airport", airport);
		mv.addObject("airports", airportService.getAllAirports());
		mv.addObject("flights", flightService.getAllFlights());
		return mv;
	}
}
