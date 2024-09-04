package com.gabreudev.marketmobile_api.controllers;

import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pagamento")
public class PaymentController {
    @GetMapping("sucesso")
    public String handleSuccess(@RequestParam("session_id") String sessionId) throws Exception {
        Session session = Session.retrieve(sessionId);

        // Obter o ID da assinatura criada na sessão de pagamento
        String subscriptionId = session.getSubscription();

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
