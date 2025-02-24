package com.youcode.e_sales_payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private String paymentIntentId;
    private String clientSecret;
    private String status;
    private String commandeReference;
}

