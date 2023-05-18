package com.synergisticit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airport;
import com.synergisticit.repository.AirportRepository;
import com.synergisticit.service.AirportService;

import jakarta.transaction.Transactional;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired AirportRepository airportRepo;
	
	@Override
	@Transactional
	public String createAirport(Airport airport) {
		if (airport == null) return "failure";
		airportRepo.save(airport);
		return "success";
	}

	@Override
	public Airport getAirportById(Long airportId) {
		return airportRepo.findById(airportId).orElse(null);
	}

	@Override
	public List<Airport> getAllAirports() {
		return airportRepo.findAll();
	}

	@Override
	public String deleteAirport(Airport airport) {
		if(airport == null) return "failure";
		airportRepo.delete(airport);
		return "success";
	}

}
