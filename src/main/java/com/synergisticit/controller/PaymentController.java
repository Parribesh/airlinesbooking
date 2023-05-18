package com.synergisticit.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Address;
import com.synergisticit.domain.PaymentInfo;
import com.synergisticit.domain.Reservation;
import com.synergisticit.service.PaymentService;
import com.synergisticit.service.ReservationService;
import com.synergisticit.validation.PaymentValidator;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {
	
	@Autowired HttpServletRequest request;
	@Autowired ReservationService resvService;
	@Autowired PaymentService paymentService;
	@Autowired PaymentValidator paymentValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(paymentValidator);
	}
	
	@GetMapping("api/payment/paymentForm")
	public ModelAndView getPaymentForm(@ModelAttribute PaymentInfo paymentInfo, BindingResult br) {
		ModelAndView mv = new ModelAndView("paymentForm");
		return mv;
	}
	
	@PostMapping("api/payment/createPayment")
	public ModelAndView createPayment( @Validated @ModelAttribute PaymentInfo paymentInfo,  BindingResult br) {
		ModelAndView mv;
		ArrayList<Reservation> sess_resv_list = new ArrayList<>();
		
		if(!br.hasErrors()) {
			System.out.println("payment.getState()"+ paymentInfo.toString());
			mv = new ModelAndView("successForm");
			if(request.getSession().getAttribute("sess_resv") != null) {
				 sess_resv_list = (ArrayList<Reservation>)request.getSession().getAttribute("sess_resv");
			}
			paymentService.createPayment(paymentInfo);
			sess_resv_list.forEach(e -> e.setPaymentConfirmation(paymentInfo));
			sess_resv_list.forEach(e -> resvService.createReservation(e));
			mv.addObject("message", "Payment SuccessFull!!");
			mv.addObject("resv_list", sess_resv_list);
			request.getSession().setAttribute("sess_resv", sess_resv_list);
		}else {
			mv = new ModelAndView("paymentForm");
			mv.addObject("message", "Payment Unsucessful. Try Again!");
		}
		
		return mv;
	}
}
