package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;
	@NotEmpty
	private String flightNumber;
	
	@NotNull
	@ManyToOne
	private Airlines operatingAirlines;
	
	@NotNull
	@ManyToOne
	private Airport arrivalAirport;
	
	@NotNull
	@ManyToOne
	private Airport departureAirport;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate departureDate;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime departureTime;
	
	@Min(1)
	@Max(350)
	private int capacity;
	
	private int booked;
	
	private double ticketPrice;
	
	 @OneToMany(cascade=CascadeType.ALL)
	 @JsonIgnore
	private List<Passenger> passengers = new ArrayList<>();
	
}
