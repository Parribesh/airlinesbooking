package com.synergisticit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.PassangerRepository;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.service.ReservationService;

import jakarta.persistence.CascadeType;

@Service
public class PassangerServiceImpl implements PassengerService {
	
	@Autowired PassangerRepository passangerRepo;
	@Autowired ReservationService reservationService;
	@Autowired FlightService flightService;
	
	@Override
	public String createPassanger(Passenger passanger) {
		if (passanger == null) return "failure";
		passangerRepo.save(passanger);
		return "success";
	}

	@Override
	public Passenger getPassangerById(Long passangerId) {
		return passangerRepo.findById(passangerId).orElse(null);
	}

	@Override
	public List<Passenger> getAllPassanger() {
		return passangerRepo.findAll();
	}

	@Override
	public String deletePassanger(Passenger passenger) {
		if (passenger == null) return "failure";
		Reservation r = reservationService.getReservationByPassengerId(passenger.getPassengerId());
		reservationService.deleteReservation(r);
		Flight f= flightService.getFlightById(passenger.getFlight().getFlightId());
		List<Passenger> p = f.getPassengers().stream().filter(e -> e.getPassengerId() != passenger.getPassengerId()).toList();
		f.setPassengers(p);
		flightService.createFlight(f);
		passangerRepo.delete(passenger);
		return "success";
	}

}
