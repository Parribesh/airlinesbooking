package com.synergisticit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long confirmationNumber;
	
	@NotEmpty
	private String firstname;
	
	@NotEmpty
	private String lastname;
	
	@NotNull
	@Valid
	private Address billingAddress;
	
	@NotNull
	private Long cardNumber;
	
	@NotNull
	private CardType paymentType;
}
