package com.youcode.e_sales_payment.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.query.Order;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;


    private int order_id;

    private String paymentMethod;

    @OneToMany(mappedBy = "payment")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "payment")
    private List<Refund> refunds;


}
