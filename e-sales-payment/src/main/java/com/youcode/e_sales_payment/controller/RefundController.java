package com.youcode.e_sales_payment.controller;

import com.stripe.exception.StripeException;
import com.youcode.e_sales_payment.entities.Refund;
import com.youcode.e_sales_payment.repository.RefundRepository;
import com.youcode.e_sales_payment.request.RefundRequest;
import com.youcode.e_sales_payment.service.StripeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class RefundController {

    public final StripeService stripeService;
    public final RefundRepository refundRepository;
    @PostMapping("/refund")
    public ResponseEntity<String> processRefund(@RequestBody RefundRequest refundRequest) {
        try {
            String refundId = stripeService.createRefund(
                    refundRequest.getPaymentIntentId(),
                    refundRequest.getAmount());

            Refund refund = new Refund();
            refund.setRefundAmount(refundRequest.getAmount());
            refund.setRefundDate(LocalDateTime.now());
            refund.setStatus("processed");
            refundRepository.save(refund);

            return ResponseEntity.ok("Refund processed successfully. Refund ID: " + refundId);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Refund failed: " + e.getMessage());
        }
    }

}