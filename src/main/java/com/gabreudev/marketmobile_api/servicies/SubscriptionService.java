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
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
                .putMetadata("user_id", user.getId().toString())
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
        if (user.getSubscriptionId() == null) {
            throw new Exception("Usuário não possui uma assinatura associada.");
        }

        // Recupera a assinatura e a cancela
        Subscription subscription = Subscription.retrieve(user.getSubscriptionId());
        subscription.cancel();

        // Remove o subscriptionId após o cancelamento
        user.setSubscriptionId(null);
        userRepository.save(user);
    }

    // Verificar se a assinatura do usuário está ativa
    public boolean isSubscriptionActive(User user) throws Exception {
        if (user.getSubscriptionId() == null) {
            return false;
        }

        Subscription subscription = Subscription.retrieve(user.getSubscriptionId());
        return "active".equals(subscription.getStatus());
    }

    public void confirmSubscription(String subscriptionId, UUID user_id) {
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new RuntimeException("Id de usuario invalido. Nenhum usuario encontrado com o id" + user_id)
        );
        user.setSubscriptionId(subscriptionId);
        userRepository.save(user);
    }
}
