package com.youcode.e_sales_payment.repository;

import com.youcode.e_sales_payment.entities.Payment;
import com.youcode.e_sales_payment.entities.Transaction;
import com.youcode.e_sales_payment.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPayment(Payment payment);
    List<Transaction> findByStatus(TransactionStatus status);
    List<Transaction> findByTransactionDateBetween(LocalDateTime start, LocalDateTime end);
}
