package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;
	
	@NotEmpty
	private String firstname;
	
	@NotEmpty
	private String lastname;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String phone;
	
	@NotNull
	private GenderType gender;
	
	@NotNull
	private LocalDate dob;
	
	@NotNull
	private IdentificationType identificationType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Flight flight;
	
	
}
