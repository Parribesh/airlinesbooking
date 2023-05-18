package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Airlines;

@Repository
public interface AirlinesRepository extends JpaRepository<Airlines, Long> {

}
