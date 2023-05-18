package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;

@RestController
public class AirportRestController {

	@Autowired AirportService airportService;
	
	@PostMapping("restapi/airport/createAirport")
	public String createAriport(@RequestBody Airport airport) {
		return airportService.createAirport(airport);
	}
	
	@GetMapping("restapi/airport/getAirportById/{airportId}")
	public Airport getAirportById(@PathVariable Long airportId) {
		return airportService.getAirportById(airportId);
	}
	
	@GetMapping("restapi/airport/getAllAirports")
	public List<Airport> getAllAirport(){
		return airportService.getAllAirports();
	}
	
	@GetMapping("restapi/airport/deleteAirportById/{airportId}")
	public String deleteAirportById(@PathVariable Long airportId) {
		Airport a = airportService.getAirportById(airportId);
		return airportService.deleteAirport(a);
	}
	
}
