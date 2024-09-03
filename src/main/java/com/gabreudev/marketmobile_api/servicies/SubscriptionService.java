package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.user.User;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionCollection;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.SubscriptionListParams;
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
                .setSuccessUrl("https://www.youtube.com/watch?v=uKxyLmbOc0Q")
                .setCancelUrl("https://www.youtube.com/watch?v=wEWF2xh5E8s")
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
    private Customer retrieveCustomer(String customerId) throws Exception {
        return Customer.retrieve(customerId);
    }
    private SubscriptionCollection retrieveCustomerSubscriptions(String customerId) throws Exception {
        SubscriptionListParams params = SubscriptionListParams.builder()
                .setCustomer(customerId)
                .build();

        return Subscription.list(params); // Retorna todas as assinaturas do cliente
    }

    private boolean isSubscriptionActive(String customerId) throws Exception {
        SubscriptionCollection subscriptions = retrieveCustomerSubscriptions(customerId);

        for (Subscription subscription : subscriptions.getData()) {
            if ("active".equals(subscription.getStatus())) {
                return true; // Assinatura ativa encontrada
            }
        }

        return false; // Nenhuma assinatura ativa encontrada
    }

}
