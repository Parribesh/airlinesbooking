package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airlines;

@Service
public interface AirlinesService {
	
	String createAirlines(Airlines airlines);
	List<Airlines> getAllAirlines();
	String deleteAirlines(Airlines airlines);
	String updateAirlines(Long airlinesId);
	Airlines getAirlinesById(Long airlinesId);
	
	
}
