package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


	List<Flight> findByDepartureAirport_AirportCityAndArrivalAirport_AirportCity(String from, String to);


}
