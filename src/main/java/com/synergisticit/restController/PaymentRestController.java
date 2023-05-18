package com.synergisticit.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.PaymentInfo;
import com.synergisticit.service.PaymentService;

@RestController
public class PaymentRestController {
	
	@Autowired PaymentService paymentService;
	
	@PostMapping("restapi/payment/createPayment")
	public String createPayment(@RequestBody PaymentInfo payment) {
		return paymentService.createPayment(payment);
	}
}
