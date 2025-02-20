package com.youcode.e_sales_payment.repository;

import com.youcode.e_sales_payment.entities.Payment;
import com.youcode.e_sales_payment.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    List<Payment> findByOrder(Order order);
    List<Payment> findByPaymentMethod(String paymentMethod);
    List<Payment> findByTransactions_Status(TransactionStatus status);
}
