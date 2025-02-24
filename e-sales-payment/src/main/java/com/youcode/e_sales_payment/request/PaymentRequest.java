package com.youcode.e_sales_payment.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class PaymentRequest {
    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private String currency;

    @NotNull
    private String paymentMethod;

    @NotNull
    private String orderReference;
}