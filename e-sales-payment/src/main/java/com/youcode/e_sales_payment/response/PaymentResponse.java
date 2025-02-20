package com.youcode.e_sales_payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private String status;
    private String clientSecret;
    private LocalDateTime timestamp = LocalDateTime.now();
}

