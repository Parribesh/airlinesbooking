package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Reservation;
import com.synergisticit.service.ReservationService;

@RestController
public class ReservationRestController {
	
	@Autowired ReservationService reservationService;
	
	@PostMapping("restapi/reservation/createReservation")
	public String createReservation(@PathVariable Reservation reservation) {
		return reservationService.createReservation(reservation);
	}
	
	@GetMapping("restapi/reservation/getReservationById/{reservationId}")
	public Reservation getReservationById(@PathVariable Long reservationId) {
		return reservationService.getReservationById(reservationId);
	}
	
	@GetMapping("restapi/reservation/getAllReservations")
	public List<Reservation> getAllReservation(){
		return reservationService.getAllReservations();
	}
	
	@GetMapping("restapi/reservation/deleteReservationById/{reservationId}")
	public String deleteReservationById(@PathVariable Long reservationId) {
		Reservation r = reservationService.getReservationById(reservationId);
		return reservationService.deleteReservation(r);
	}
	
	
}
