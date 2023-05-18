package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Passenger;
import com.synergisticit.service.PassengerService;

@RestController
public class PassengerRestController {
	
	@Autowired PassengerService passengerService;
	
	@PostMapping(value ="restapi/passenger/createPassenger", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String createPassenger(@RequestBody Passenger passenger) {
		return passengerService.createPassanger(passenger);
	}
	
	@GetMapping("restapi/passenger/getPassengerById/{passengerId}")
	public Passenger getPassengerById(@PathVariable Long passengerId) {
		return passengerService.getPassangerById(passengerId);
	}
	
	@GetMapping("restapi/passenger/getAllPassengers")
	public List<Passenger> getAllPassengers(){
		return passengerService.getAllPassanger();
	}
	
	@GetMapping("restapi/passenger/deletePassengerById/{passengerId}")
	public String deletePassengerById(@PathVariable Long passengerId) {
		Passenger p = passengerService.getPassangerById(passengerId);
		return passengerService.deletePassanger(p);
	}
	
}
