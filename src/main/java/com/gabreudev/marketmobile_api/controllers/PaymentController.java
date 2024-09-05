package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.servicies.SubscriptionService;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("pagamento")
public class PaymentController {

    @Autowired
    SubscriptionService service;

    @GetMapping("sucesso")
    public String handleSuccess(@RequestParam("session_id") String sessionId, @AuthenticationPrincipal User user) throws Exception {
        Session session = Session.retrieve(sessionId);

        // Obter o ID da assinatura criada na sessão de pagamento
        String subscriptionId = session.getSubscription();

        String userId = session.getMetadata().get("user_id");
        UUID id = UUID.fromString(userId);
        service.confirmSubscription(subscriptionId, id);

        // Recuperar a assinatura do Stripe usando o subscriptionId
        Subscription subscription = Subscription.retrieve(subscriptionId);

        // Verificar o status da assinatura
        if ("active".equals(subscription.getStatus())) {
            // A assinatura está ativa, faça o que for necessário, como marcar o usuário como ativo no sistema
            return "Assinatura ativa!";
        } else {
            // A assinatura não está ativa, trate isso conforme necessário
            return "Assinatura não está ativa.";
        }
    }

}
