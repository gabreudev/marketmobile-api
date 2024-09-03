package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.user.User;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;

public class SubscriptionService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.price.id}")
    private String priceId;

    public SubscriptionService() {
        Stripe.apiKey = stripeSecretKey;
    }

    public Session createCheckoutSession(User user) throws Exception {
        SessionCreateParams params = SessionCreateParams.builder()
                .setCustomer(user.getCustomerId())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setSuccessUrl("https://your-success-url.com/success")
                .setCancelUrl("https://your-cancel-url.com/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPrice(priceId)
                                .setQuantity(1L)
                                .build())
                .build();

        return Session.create(params);
    }
    public Customer createCustomer(User user) throws Exception {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setEmail(user.getEmail())
                .setName(user.getUsername())
                .build();

        return Customer.create(params);
    }
}
