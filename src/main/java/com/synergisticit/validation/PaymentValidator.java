package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.synergisticit.domain.PaymentInfo;

@Component
public class PaymentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PaymentInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PaymentInfo paymentInfo = (PaymentInfo)target;
		
		if(paymentInfo.getCardNumber() == null) {
			errors.rejectValue("cardNumber", "paymentInfo.cardNumber");
		}
		
	}

}
