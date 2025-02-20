package com.youcode.e_sales_payment.service;

import com.stripe.StripeClient;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final StripeClient stripeClient;
    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        try {
            // Create Stripe Payment Intent
            PaymentIntent paymentIntent = PaymentIntent.create(PaymentIntentCreateParams.builder()
                    .setAmount(paymentRequest.getAmount().multiply(new BigDecimal("100")).longValue())
                    .setCurrency("eur")
                    .setPaymentMethod(paymentRequest.getPaymentMethodId())
                    .setConfirm(true)
                    .build());

            // Create Payment record
            Payment payment = new Payment();
            payment.setAmount(paymentRequest.getAmount());
            payment.setOrder(paymentRequest.getOrder());
            payment.setPaymentMethod(paymentRequest.getPaymentMethodId());
            paymentRepository.save(payment);

            // Create Transaction record
            Transaction transaction = new Transaction();
            transaction.setPayment(payment);
            transaction.setReference(paymentIntent.getId());
            transaction.setStatus(paymentIntent.getStatus());
            transaction.setTransactionDate(LocalDateTime.now());
            transactionRepository.save(transaction);

            return new PaymentResponse(payment.getId(), paymentIntent.getStatus(), paymentIntent.getClientSecret());
        } catch (StripeException e) {
            throw new PaymentProcessingException("Payment processing failed: " + e.getMessage());
        }
    }

    public RefundResponse processRefund(RefundRequest refundRequest) {
        try {
            Payment payment = paymentRepository.findById(refundRequest.getPaymentId())
                    .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));

            Refund refund = Refund.create(RefundCreateParams.builder()
                    .setPaymentIntent(payment.getTransactions().get(0).getReference())
                    .setAmount(refundRequest.getAmount().multiply(new BigDecimal("100")).longValue())
                    .build());

            // Create Refund record
            Refund refundRecord = new Refund();
            refundRecord.setPayment(payment);
            refundRecord.setRefundedAmount(refundRequest.getAmount());
            refundRecord.setRefundDate(LocalDateTime.now());
            refundRecord.setStatus(refund.getStatus());

            return new RefundResponse(refundRecord.getId(), refund.getStatus());
        } catch (StripeException e) {
            throw new RefundProcessingException("Refund processing failed: " + e.getMessage());
        }
    }
}