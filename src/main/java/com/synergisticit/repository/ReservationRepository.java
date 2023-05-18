package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Reservation findByPassenger_PassengerId(Long passengerId);

}
