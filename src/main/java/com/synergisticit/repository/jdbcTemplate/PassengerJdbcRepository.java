package com.synergisticit.repository.jdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.synergisticit.domain.Passenger;

public class PassengerJdbcRepository {
	
	@Autowired NamedParameterJdbcTemplate npjtemplate;
	
	public String createPassenger(Passenger passenger) {
		String query = "insert into passenger values(:firstname, :lastname, :email, :phone, :gender: , :dob, :identificationType)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("firstname", passenger.getFirstname());
		map.put("lastname", passenger.getLastname());
		map.put("email", passenger.getEmail());
		map.put("phone", passenger.getPhone());
		map.put("gender", passenger.getGender());
		map.put("dob", passenger.getDob());
		map.put("identificationType", passenger.getIdentificationType());
		
		npjtemplate.update(query, map);
		
		return "success";
		
	}
}
