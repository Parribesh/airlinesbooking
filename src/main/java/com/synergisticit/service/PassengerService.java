package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Passenger;

@Service
public interface PassengerService {
	
	String createPassanger(Passenger passanger);
	Passenger getPassangerById(Long passangerId);
	List<Passenger> getAllPassanger();
	String deletePassanger(Passenger passanger);
}
