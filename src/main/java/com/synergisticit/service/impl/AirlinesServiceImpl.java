package com.synergisticit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.repository.AirlinesRepository;
import com.synergisticit.service.AirlinesService;

@Service
public class AirlinesServiceImpl implements AirlinesService {
	
	@Autowired AirlinesRepository airlinesRepo;

	@Override
	public String createAirlines(Airlines airlines) {
		if(airlines != null) {
			if(airlinesRepo.save(airlines) != null) return "success";
			else return "failure";
		}
		return "no data to save";
	}

	@Override
	public Airlines getAirlinesById(Long airlinesId) {	
		return airlinesRepo.findById(airlinesId).orElse(null);
	}

	@Override
	public List<Airlines> getAllAirlines() {
		return airlinesRepo.findAll();
	}

	@Override
	public String deleteAirlines(Airlines airlines) {
		if(airlines != null) {
			airlinesRepo.delete(airlines);
			return "success";
		}
		else return "failure";
	}

	@Override
	public String updateAirlines(Long airlinesId) {
		// TODO Auto-generated method stub
		return null;
	}

}
