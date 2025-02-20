package com.youcode.e_sales_payment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private BigDecimal amount;
    private String paymentMethodId;
    private int order;
    private String currency = "EUR";
    private String description;
}
