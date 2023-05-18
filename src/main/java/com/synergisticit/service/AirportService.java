package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airport;

@Service
public interface AirportService {
	
	String createAirport(Airport airport);
	Airport getAirportById(Long airportId);
	List<Airport> getAllAirports();
	String deleteAirport(Airport airport);
}
