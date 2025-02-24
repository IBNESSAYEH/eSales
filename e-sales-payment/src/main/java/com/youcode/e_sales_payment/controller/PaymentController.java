package com.youcode.e_sales_payment.controller;



import com.stripe.exception.StripeException;
import com.youcode.e_sales_payment.entities.Payment;
import com.youcode.e_sales_payment.entities.Refund;
import com.youcode.e_sales_payment.repository.PaymentRepository;
import com.youcode.e_sales_payment.request.PaymentRequest;
import com.youcode.e_sales_payment.request.RefundRequest;
import com.youcode.e_sales_payment.response.PaymentResponse;
import com.youcode.e_sales_payment.service.StripeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {


    private StripeService stripeService;


    private PaymentRepository paymentRepository;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            String transactionId = stripeService.createPaymentIntent(
                    paymentRequest.getAmount(),
                    paymentRequest.getCurrency(),
                    paymentRequest.getPaymentMethod());

            Payment payment = new Payment();
            payment.setAmount(paymentRequest.getAmount());
            payment.setOrderReference(paymentRequest.getOrderReference());
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            payment.setStatus("success");
            payment.setPaymentDate(LocalDateTime.now());
            paymentRepository.save(payment);

            return ResponseEntity.ok("Payment processed successfully. Transaction ID: " + transactionId);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
        }
    }
}