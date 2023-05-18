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

import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PassengerController {
	
	@Autowired PassengerService passengerService;
	@Autowired FlightService flightService;
	@Autowired ReservationService reservService;
	@Autowired HttpServletRequest request;
	
	
	@GetMapping("/api/passenger/passengerForm")
	public ModelAndView getPassengerForm(@ModelAttribute Passenger passenger, BindingResult br) {
		ModelAndView mv = new ModelAndView("passengerForm");
		
		mv.addObject("passenger", passenger);
		mv.addObject("passengers", passengerService.getAllPassanger());
		return mv;
	}
	
	@PostMapping("/api/passenger/createPassenger")
	public ModelAndView createPassenger(@Validated @ModelAttribute Passenger passenger, BindingResult br,  @ModelAttribute Reservation reservation) {
		ModelAndView mv;
		System.out.println("flight: "+ passenger.getFlight());
		if(!br.hasErrors()) {
			passengerService.createPassanger(passenger);
			mv = new ModelAndView("reservationForm");
			}else {
				mv = new ModelAndView("passengerForm");
			}
		System.out.println("flight: "+ passenger.getFlight());
		mv.addObject("showReservation", true);
		mv.addObject("totalReservations", reservService.getAllReservations().size());
		System.out.println("totalReservations " + reservService.getAllReservations().size());
		mv.addObject("passenger", passenger);
		mv.addObject("flight", passenger.getFlight());
		mv.addObject("passengers", passengerService.getAllPassanger());

		return mv;
	}
	
	@GetMapping("/api/passenger/updatePassenger/{passengerId}")
	public ModelAndView updatePassenger(@PathVariable Long passengerId,  @ModelAttribute Passenger passenger) {
		ModelAndView mv = new ModelAndView("passengerForm");
		Passenger pr = passengerService.getPassangerById(passengerId);

		mv.addObject("p", pr);
		mv.addObject("passenger", passenger);
		mv.addObject("passengers", passengerService.getAllPassanger());
		return mv;
	}
	@GetMapping("/api/passenger/reserveFlight/{flightId}")
	public ModelAndView reserveFlight(@PathVariable Long flightId,  @ModelAttribute Passenger passenger, BindingResult br) {
		ModelAndView mv = new ModelAndView("passengerForm");
		mv.addObject("passenger", passenger);
		mv.addObject("flight", flightService.getFlightById(flightId));
		
		request.getSession().setAttribute("flight", flightService.getFlightById(flightId));
		mv.addObject("passengers", passengerService.getAllPassanger());
		int currentPassenger = (int) request.getSession().getAttribute("currentPassenger");
		mv.addObject("passengerNo", currentPassenger);
		return mv;
	}
	
	@GetMapping("/api/passenger/deletePassenger/{passengerId}")
	public ModelAndView deletePassenger(@PathVariable Long passengerId,  @ModelAttribute Passenger passenger) {
		ModelAndView mv = new ModelAndView("passengerForm");
		Passenger pr = passengerService.getPassangerById(passengerId);
		passengerService.deletePassanger(pr);
		mv.addObject("passenger", passenger);
		mv.addObject("passengers", passengerService.getAllPassanger());

		return mv;
	}
}
