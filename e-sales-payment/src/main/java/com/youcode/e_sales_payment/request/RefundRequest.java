package com.youcode.e_sales_payment.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class RefundRequest {
    @NotNull
    private String paymentIntentId;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private String reason;
}