package com.youcode.e_sales_payment.repository;

import com.youcode.e_sales_payment.entities.Payment;
import com.youcode.e_sales_payment.entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByPayment(Payment payment);
    List<Refund> findByStatus(String status);
    List<Refund> findByRefundDateBetween(LocalDateTime start, LocalDateTime end);
}
