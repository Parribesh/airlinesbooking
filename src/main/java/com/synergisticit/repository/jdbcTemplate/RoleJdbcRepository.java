package com.synergisticit.repository.jdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Role;

@Repository
public class RoleJdbcRepository {
	
	@Autowired NamedParameterJdbcTemplate npJdbcTemplate;
	
	public String createRole(Role r) {
		String sql = "insert into role values(:roleId, :roleName)";
		
		System.out.println("r.getRoleId(): "+r.getRoleId());
		System.out.println("r.getRoleName(): "+r.getRoleName());
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", r.getRoleId());
		map.put("roleName", Integer.valueOf(2));
		
		int rowsInserted = npJdbcTemplate.update(sql, map);

		System.out.println("rowsInserted: "+ rowsInserted);
		if(rowsInserted == 1) {
			return "success";
		}else {
			return "failure";
		}
		
	}
	
	//TODO:Check if it's implemented properly
	public String deleteRole(Role r) {
		String query = "delete role where roleId=:roleId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", r.getRoleId());
		
		int rowsModified = npJdbcTemplate.update(query, map);
		if(rowsModified > 0) {
			return "success";
		}
		return "failure";
	}
}
