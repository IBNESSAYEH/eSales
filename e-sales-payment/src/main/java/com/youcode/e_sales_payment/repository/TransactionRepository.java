package com.youcode.e_sales_payment.repository;


import com.youcode.e_sales_payment.entities.Refund;
import com.youcode.e_sales_payment.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
