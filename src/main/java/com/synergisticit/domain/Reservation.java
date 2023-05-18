package com.synergisticit.domain;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketNumber;
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	private Passenger passenger;
	
	@NotNull
	@OneToOne
	private Flight flight;
	
	
	private int checkedBags;
	
	private boolean checkedIn;
	
	@ManyToOne
	private PaymentInfo paymentConfirmation;
	
	
	
}
