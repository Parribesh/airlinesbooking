package com.synergisticit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.PaymentInfo;
import com.synergisticit.repository.PaymentRepository;
import com.synergisticit.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired PaymentRepository paymentRepo;
	
	@Override
	public String createPayment(PaymentInfo payment) {
		if(paymentRepo.save(payment) != null) return "success";
		return "failure";
	}

}
