package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Flight;
import com.synergisticit.service.FlightService;

@RestController
public class FlightRestController {

	@Autowired FlightService flightService;
	
	@PostMapping("restapi/flight/createFlight")
	public String createFlight(@RequestBody Flight flight) {
		return flightService.createFlight(flight);
	}
	
	@GetMapping("restapi/flight/getFlightById/{flightId}")
	public Flight getFlightById(@PathVariable Long flightId) {
		return flightService.getFlightById(flightId);
	}
	
	@GetMapping("restapi/flight/getAllFlights")
	public List<Flight> getAllFlights(){
		return flightService.getAllFlights();
	}
	
	@GetMapping("restapi/flight/deleteFlightById/{flightId}")
	public String deleteFlightById(@PathVariable Long flightId) {
		Flight f = flightService.getFlightById(flightId);
		return flightService.deleteFlight(f);
	}
}
