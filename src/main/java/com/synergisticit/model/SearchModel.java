package com.synergisticit.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchModel {
	
	String to;
	String from;
	LocalDate date;
	int passengers;
}
