package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.PaymentInfo;
import com.synergisticit.domain.Reservation;
import com.synergisticit.domain.RoleTypes;
import com.synergisticit.model.ResSearchModel;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReservationController {
	
	@Autowired ReservationService reservService;
	@Autowired FlightService flightService;
	@Autowired HttpServletRequest request;
	@Autowired PassengerService passengerService;
	
	@GetMapping("/api/reservation/reservationForm")
	public ModelAndView getReservationForm(@ModelAttribute Reservation reservation) {
		ModelAndView mv = new ModelAndView("reservationForm");
		mv.addObject("totalReservations", reservService.getAllReservations().size());
		System.out.println("reservations, " + reservService.getAllReservations().size());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getAuthorities().stream().map(e -> e.getAuthority()).toList().contains(RoleTypes.ADMIN.name())) {
		mv.addObject("reservations", reservService.getAllReservations());
		}else {
			mv.addObject("reservations", null);
		}
		mv.addObject("reservation", reservation);
		return mv;
		
	}
	
	@GetMapping("/api/reservation/searchReservation")
	public ModelAndView searchReservation( @ModelAttribute ResSearchModel resSearchModel ) {
		ModelAndView mv = new ModelAndView("searchReservation");
		mv.addObject("confirmedReservations", null);
		return mv;
	}
	
	@PostMapping("/api/reservation/searchReservation")
	public ModelAndView getReservation( @ModelAttribute ResSearchModel resSearchModel ) {
		ModelAndView mv = new ModelAndView("searchReservation");
		List<Reservation> confirmedReservations = new ArrayList<>();
		
		if(resSearchModel.getConfirmationNo() != "") {
			try {

				confirmedReservations = reservService.getAllReservations().stream()
						.filter(e -> {
							if(e.getPaymentConfirmation() != null) {
								return e.getPaymentConfirmation().getConfirmationNumber()== Long.valueOf(resSearchModel.getConfirmationNo());
							}else {
								return false;
							}
									}).toList();
				
			}catch(Exception e) {
				System.out.println("Number format excetion "+ e.getMessage());
			}
			 		
		}else {
			if(resSearchModel.getTicketNo() != null) {
				Long ticketNum = resSearchModel.getTicketNo();
			confirmedReservations = reservService.getAllReservations().stream().filter
									(e -> e.getTicketNumber() == ticketNum).toList();
			System.out.println("happened");
			}
		}
//		confirmedReservations.forEach(e -> System.out.println(e.g))
		System.out.println("confirmedReservations: " + confirmedReservations.size());
		mv.addObject("confirmedReservations", confirmedReservations);
		
		return mv;
	}
	
	@PostMapping("/api/reservation/createReservation")
	public ModelAndView createReservation(@ModelAttribute PaymentInfo paymentInfo, @ModelAttribute Reservation reservation,@ModelAttribute Passenger passenger,  BindingResult br) {
		int currentPassenger = 0;
		int numberOfPassengers = 0;
		var sess_resv_list = new ArrayList<>();
		
		if(!br.hasErrors()) {
			reservation.setCheckedIn(false);
	
			reservService.createReservation(reservation);
			Flight flight = flightService.getFlightById(reservation.getFlight().getFlightId());
			if(flight != null) {
				flight.getPassengers().add(passenger);
				flight.setBooked(flight.getPassengers().size());
				flightService.createFlight(flight);
				
				 if(request.getSession().getAttribute("sess_resv") != null) {
					 sess_resv_list = (ArrayList<Object>)request.getSession().getAttribute("sess_resv");
					 System.out.println("size session: "+ sess_resv_list.size());
					 sess_resv_list.add(reservation);
					 request.getSession().setAttribute("sess_resv", sess_resv_list);
				 }else {
					 System.out.println("Wass null session");
					 sess_resv_list = new ArrayList<>();
					 sess_resv_list.add(reservation);
					 request.getSession().setAttribute("sess_resv", sess_resv_list);
				 }	
			}
			numberOfPassengers = (int) request.getSession().getAttribute("totalPassengers");
			currentPassenger  = (int) request.getSession().getAttribute("currentPassenger") + 1;

			request.getSession().setAttribute("currentPassenger", currentPassenger);
			
		}
		
		ModelAndView mv;		
		if(currentPassenger <= numberOfPassengers) { 
			 mv = new ModelAndView("passengerForm");
			 mv.addObject("passengerNo", currentPassenger);
			 mv.addObject("passenger", new Passenger());
			 mv.addObject("flight", (Flight)request.getSession().getAttribute("flight"));

		}else{
			mv = new ModelAndView("paymentForm");
			mv.addObject("resv_list", sess_resv_list);
		}
		
		mv.addObject("passengers", passengerService.getAllPassanger());
		mv.addObject("totalReservations", reservService.getAllReservations().size());
		mv.addObject("reservations", reservService.getAllReservations());
		mv.addObject("reservation", reservation);
		return mv;
		
	}
	
	@GetMapping("/api/reservation/delteReservation/{reservationId}")
	public ModelAndView deleteReservation(@PathVariable Long reservationId, @ModelAttribute Reservation reservation, BindingResult br) {
		ModelAndView mv = new ModelAndView("reservationForm");
		var rv = reservService.getReservationById(reservationId);
		if(!br.hasErrors()) {
			reservService.deleteReservation(rv);
			}
		mv.addObject("reservations", reservService.getAllReservations());
		mv.addObject("reservation", reservation);
		return mv;
		
	}
	
	@GetMapping("/api/reservation/updateReservation/{reservationId}")
	public ModelAndView updateReservation(@PathVariable Long reservationId, @ModelAttribute Reservation reservation, BindingResult br) {
		ModelAndView mv = new ModelAndView("reservationForm");
		var rv = reservService.getReservationById(reservationId);
		mv.addObject("r", rv);
		mv.addObject("reservations", reservService.getAllReservations());
		mv.addObject("reservation", reservation);
		return mv;
		
	}
	
	@GetMapping("/api/reservation/reserveFlight/{flightId}")
	public ModelAndView reservFlight(@PathVariable Long flightId, @ModelAttribute Reservation reservation, BindingResult br) {
		ModelAndView mv = new ModelAndView("reservationForm");
		mv.addObject("f", flightService.getFlightById(flightId));
		mv.addObject("reservations", reservService.getAllReservations());
		mv.addObject("reservation", reservation);
		return mv;
		
	}
}
