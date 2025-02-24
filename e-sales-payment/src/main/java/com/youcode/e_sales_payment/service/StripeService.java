package com.youcode.e_sales_payment.service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${secret_key}")
    private String stripeSecretKey;

    public String createPaymentIntent(Double amount, String currency, String paymentMethod) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100))
                .setCurrency(currency)
                .setPaymentMethod(paymentMethod)
                .setConfirm(true)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getId();
    }

    public String createRefund(String paymentIntentId, Double amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        RefundCreateParams params = RefundCreateParams.builder()
                .setPaymentIntent(paymentIntentId)
                .setAmount((long) (amount * 100))
                .build();

        Refund refund = Refund.create(params);
        return refund.getId();
    }
}