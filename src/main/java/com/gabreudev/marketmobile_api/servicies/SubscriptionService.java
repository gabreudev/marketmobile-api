package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.repositories.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionCollection;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.SubscriptionListParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.price.id}")
    private String priceId;

    @Autowired
    private UserRepository userRepository;

    public SubscriptionService(@Value("${stripe.secret.key}") String stripeSecretKey) {
        this.stripeSecretKey = stripeSecretKey;
        Stripe.apiKey = this.stripeSecretKey;
    }

    public Session createCheckoutSession(User user) throws Exception {

        if(user.getCustomerId() ==  null){
            Customer customer = createCustomer(user);
            user.setCustomerId(customer.getId());
        }

        String BaseUrl = "http://localhost:8080";
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setSuccessUrl(BaseUrl+"/pagamento/sucesso?session_id={CHECKOUT_SESSION_ID}")
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

        Customer customer = Customer.create(params);
        user.setCustomerId(customer.getId());
        userRepository.save(user);
        return customer;
    }

    public void cancelSubscription(User user) throws Exception {
        if (user.getCustomerId() == null) {
            throw new Exception("Usuário não possui um customerId associado.");
        }

        SubscriptionCollection subscriptions = retrieveCustomerSubscriptions(user.getCustomerId());

        for (Subscription subscription : subscriptions.getData()) {
            if ("active".equals(subscription.getStatus())) {
                // Cancela a assinatura
                Subscription canceledSubscription = subscription.cancel();
                System.out.println("Assinatura cancelada: " + canceledSubscription.getId());
                break;  // Cancela apenas a primeira assinatura ativa encontrada
            }
        }
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

        return false;
    }

}
