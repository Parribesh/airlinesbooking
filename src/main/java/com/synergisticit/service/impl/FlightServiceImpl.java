package com.synergisticit.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.model.SearchModel;
import com.synergisticit.repository.FlightRepository;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;

import jakarta.transaction.Transactional;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired FlightRepository flightRepo;
	@Autowired AirportService airportService;
	
	@Override
	@Transactional
	public String createFlight(Flight flight) {
		if (flight == null) return "failure";
		Airport arrivalAirport = airportService.getAirportById(flight.getArrivalAirport().getAirportId());
		Airport departureAirport = airportService.getAirportById(flight.getDepartureAirport().getAirportId());
		
		if(!arrivalAirport.getArrivalFlights().contains(flight)) arrivalAirport.getArrivalFlights().add(flight);
		
		Set<Flight> departureList = departureAirport.getDepartureFlights();
		if(!departureList.contains(flight)) departureList.add(flight);
		flightRepo.save(flight);
		airportService.createAirport(arrivalAirport);
		airportService.createAirport(departureAirport);
		
		return "success";
	}

	@Override
	public Flight getFlightById(Long flightId) {
		return flightRepo.findById(flightId).orElse(null);
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
	}

	@Override
	public String deleteFlight(Flight flight) {
		// TODO Auto-generated method stub
		if (flight == null )return "failure";
		flightRepo.delete(flight);
		return "success";
	}

	@Override
	public List<Flight> searchFlights(SearchModel searchModel) {
		return flightRepo.findByDepartureAirport_AirportCityAndArrivalAirport_AirportCity(searchModel.getFrom(), searchModel.getTo());
		
	}

}
