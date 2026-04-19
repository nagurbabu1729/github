package com.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
