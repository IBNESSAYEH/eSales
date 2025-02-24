package com.youcode.e_sales_payment.repository;

import com.youcode.e_sales_payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderReference(String orderReference);
}