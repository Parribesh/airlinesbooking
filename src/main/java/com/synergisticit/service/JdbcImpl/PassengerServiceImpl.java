package com.synergisticit.service.JdbcImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.jdbcTemplate.PassengerJdbcRepository;
import com.synergisticit.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {

	@Autowired PassengerJdbcRepository passengerJdbcRepo;
	
	@Override
	public String createPassanger(Passenger passanger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Passenger getPassangerById(Long passangerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passenger> getAllPassanger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePassanger(Passenger passanger) {
		// TODO Auto-generated method stub
		return null;
	}

}
