package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Airport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long airportId;
	@NotEmpty
	private String airportName;
	@NotEmpty
	private String airportCode;
	@NotEmpty
	private String airportCity;
	
	@OneToMany
	@JsonIgnore
	private Set<Flight> arrivalFlights = new HashSet<>();
	
	@OneToMany
	@JsonIgnore
	private Set<Flight> departureFlights = new HashSet<>();
	

}
