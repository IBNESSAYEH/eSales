package com.youcode.e_sales_payment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundRequest {
    private Long paymentId;
    private BigDecimal amount;
    private String reason;
}
