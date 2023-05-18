package com.synergisticit.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;
import com.synergisticit.model.SearchModel;

@Service
public interface FlightService {
	
	String createFlight(Flight flight);
	Flight getFlightById(Long flightId);
	List<Flight> getAllFlights();
	String deleteFlight(Flight flight);
	List<Flight> searchFlights(SearchModel searchModel);
	
}
