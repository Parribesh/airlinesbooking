package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Passenger;

@Repository
public interface PassangerRepository extends JpaRepository<Passenger, Long> {

}
