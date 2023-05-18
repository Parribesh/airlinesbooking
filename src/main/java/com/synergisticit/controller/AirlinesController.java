package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Airlines;
import com.synergisticit.service.AirlinesService;
import com.synergisticit.service.FlightService;

@Controller
public class AirlinesController {
	
	@Autowired AirlinesService airlinesService;
	@Autowired FlightService flightService;
	
	@GetMapping("/api/airlines/airlinesForm")
	public ModelAndView getAirlinesForm(@ModelAttribute Airlines airline, BindingResult br) {
		ModelAndView mv = new ModelAndView("airlinesForm");
		mv.addObject("airline", airline);
		mv.addObject("flights", flightService.getAllFlights());
		mv.addObject("airlines", airlinesService.getAllAirlines());	
		return mv;
	}
	
	@PostMapping("/api/airlines/createAirlines")
	public ModelAndView createAirlines(@Validated @ModelAttribute("airline") Airlines airline, BindingResult br) {
		ModelAndView mv = new ModelAndView("airlinesForm");
		System.out.println("airline: "+ airline.getAirlinesName());
		if(!br.hasErrors()) {
			airlinesService.createAirlines(airline);
		}
		mv.addObject("flights", flightService.getAllFlights());
		mv.addObject("airline", airline);
		mv.addObject("message", "success");
		mv.addObject("airlines", airlinesService.getAllAirlines());	
		return mv;
	}
	
	@GetMapping("/api/airlines/updateAirlines/{airlinesId}")
	public ModelAndView updateAirlines(@PathVariable Long airlinesId,  @ModelAttribute Airlines airline) {
		ModelAndView mv = new ModelAndView("airlinesForm");
		Airlines ar = airlinesService.getAirlinesById(airlinesId);
		mv.addObject("a", ar);
		mv.addObject("airline", airline);
		mv.addObject("airlines", airlinesService.getAllAirlines());
		mv.addObject("flights", flightService.getAllFlights());
		return mv;
	}
	
	@GetMapping("/api/airlines/deleteAirlines/{airlinesId}")
	public ModelAndView deleteAirlines(@PathVariable Long airlinesId,  @ModelAttribute Airlines airline) {
		ModelAndView mv = new ModelAndView("airlinesForm");
		Airlines ar = airlinesService.getAirlinesById(airlinesId);
		airlinesService.deleteAirlines(ar);
		mv.addObject("airline", airline);
		mv.addObject("airlines", airlinesService.getAllAirlines());	
		mv.addObject("flights", flightService.getAllFlights());
		return mv;
	}
}
