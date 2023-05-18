package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Airlines;
import com.synergisticit.service.AirlinesService;

@RestController
public class AirlinesRestController {
	
	@Autowired AirlinesService airlinesService;
	
	@PostMapping("restapi/airlines/createAirlines")
	public String createAirlines(@RequestBody Airlines airlines) {
		return airlinesService.createAirlines(airlines);
	}
	
	@GetMapping("restapi/airlines/getAirlinesById/{airlinesId}")
	public Airlines getAirlinesById(@PathVariable Long airlinesId) {
		return airlinesService.getAirlinesById(airlinesId);
	}
	
	@GetMapping("restapi/airlines/getAllAirlines")
	public List<Airlines> getAllAirlines(){
		return airlinesService.getAllAirlines();
	}
	
	@GetMapping("restapi/airlines/deleteAirlinesById/{airlinesId}")
	public String deleteAirlinesById(@PathVariable Long airlinesId) {
		Airlines a = airlinesService.getAirlinesById(airlinesId);
		return airlinesService.deleteAirlines(a);
	}
}
