package com.youcode.e_sales_payment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double refundAmount;
    private LocalDateTime refundDate;
    private String status;
    private String reason;
    private String refundMethod;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "refund", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
