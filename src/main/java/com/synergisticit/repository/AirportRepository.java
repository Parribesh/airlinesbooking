package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
