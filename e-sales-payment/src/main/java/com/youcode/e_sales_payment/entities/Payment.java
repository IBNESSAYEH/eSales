package com.youcode.e_sales_payment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String orderReference;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;
    private LocalDateTime expirationDate;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Refund> refunds;
}
