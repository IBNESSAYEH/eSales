package com.youcode.e_sales_payment.config;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import com.youcode.e_sales_payment.entities.Refund;
import com.youcode.e_sales_payment.request.PaymentRequest;
import com.youcode.e_sales_payment.request.RefundRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class StripeConfig {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Bean
    public StripeClient stripeClient() {
        return new StripeClient(stripeSecretKey);
    }
}

@Component
@RequiredArgsConstructor
public class StripeClient {
    private final String secretKey;

    public PaymentIntent createPaymentIntent(PaymentRequest paymentRequest) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentRequest.getAmount().multiply(new BigDecimal("100")).longValue())
                .setCurrency(paymentRequest.getCurrency().toLowerCase())
                .setPaymentMethod(paymentRequest.getPaymentMethodId())
                .setConfirm(true)
                .setDescription(paymentRequest.getDescription())
                .build();

        return PaymentIntent.create(params);
    }

    public Refund createRefund(RefundRequest refundRequest, String paymentIntentId) throws StripeException {
        RefundCreateParams params = RefundCreateParams.builder()
                .setPaymentIntent(paymentIntentId)
                .setAmount(refundRequest.getAmount().multiply(new BigDecimal("100")).longValue())
                .setReason(RefundCreateParams.Reason.REQUESTED_BY_CUSTOMER)
                .build();

        return Refund.create(params);
    }
}
