package com.youcode.e_sales_payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundResponse {
    private Long refundId;
    private String status;
    private LocalDateTime timestamp = LocalDateTime.now();
}

