package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Reservation;

@Service
public interface ReservationService {
	
	String createReservation(Reservation reservation);
	Reservation getReservationById(Long reservationId);
	List<Reservation> getAllReservations();
	String deleteReservation(Reservation reservation);
	Reservation getReservationByPassengerId(Long passengerId);
}
