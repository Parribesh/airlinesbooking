package com.synergisticit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.ReservationRepository;
import com.synergisticit.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired ReservationRepository reservationRepo;

	@Override
	public String createReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		if (reservation == null) return "failure";
		reservationRepo.save(reservation);
		return "success";
	}

	@Override
	public Reservation getReservationById(Long reservationId) {
		return reservationRepo.findById(reservationId).orElse(null);
	}

	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepo.findAll();
	}

	@Override
	public String deleteReservation(Reservation reservation) {
		if (reservation == null) return "failure";
		reservationRepo.delete(reservation);
		return "success";
	}

	@Override
	public Reservation getReservationByPassengerId(Long passengerId) {
		return reservationRepo.findByPassenger_PassengerId(passengerId);
	}

}
