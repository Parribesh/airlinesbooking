package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.PaymentInfo;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfo, Long> {

}
