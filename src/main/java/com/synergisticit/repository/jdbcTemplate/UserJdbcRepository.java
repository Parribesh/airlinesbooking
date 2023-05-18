package com.synergisticit.repository.jdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.User;

@Repository
public class UserJdbcRepository {
	
	@Autowired NamedParameterJdbcTemplate npJdbcTemplate;
	
	public String createUser(User u) {
		String query = "Inser into user values(:username, :userPassword, :userEmail, :userMobile, :userRoles)";
		
		Map<String, Object> map = new HashMap<>();
		
		int rowsInserted = npJdbcTemplate.update(query, map);
		
		if(rowsInserted > 0) return "success";
		return "failure";
	}
}
