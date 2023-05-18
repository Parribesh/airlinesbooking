package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
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
public class Airlines {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airlinesId;
	
	@NotEmpty
	private String airlinesName;
	
	@NotEmpty
	private String airlinesCode;
	
	@OneToMany
	@JsonIgnore
	private List<Flight> flight = new ArrayList<>();
	
	
}
