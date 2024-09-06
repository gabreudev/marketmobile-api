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

    // Success page with HTML and CSS
    @GetMapping("sucesso")
    public String handleSuccess(@RequestParam("session_id") String sessionId, @AuthenticationPrincipal User user) throws Exception {
        Session session = Session.retrieve(sessionId);

        String subscriptionId = session.getSubscription();

        String userId = session.getMetadata().get("user_id");
        UUID id = UUID.fromString(userId);
        service.confirmSubscription(subscriptionId, id);
        Subscription subscription = Subscription.retrieve(subscriptionId);

        if ("active".equals(subscription.getStatus())) {
            // Returning an HTML success page with inline CSS
            return "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f0f8ff; }" +
                    ".message-box { border: 2px solid #4CAF50; background-color: #d4edda; padding: 20px; display: inline-block; border-radius: 10px; }" +
                    "h1 { color: #4CAF50; }" +
                    "p { color: #155724; font-size: 18px; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='message-box'>" +
                    "<h1>Assinatura Ativa!</h1>" +
                    "<p>Sua assinatura foi confirmada com sucesso. Volte para o aplicativo.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
        }
            return "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #fff3cd; }" +
                    ".message-box { border: 2px solid #856404; background-color: #ffeeba; padding: 20px; display: inline-block; border-radius: 10px; }" +
                    "h1 { color: #856404; }" +
                    "p { color: #856404; font-size: 18px; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='message-box'>" +
                    "<h1>Erro na Assinatura</h1>" +
                    "<p>A assinatura não está ativa. Ocorreu um erro. Contate os administradores do sistema para mais detalhes.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
        }


    @GetMapping("erro")
    public String handleCancel() {
        // Returning an HTML error page with inline CSS
        return "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; background-color: #f8d7da; }" +
                ".message-box { border: 2px solid #f5c6cb; background-color: #f8d7da; padding: 20px; display: inline-block; border-radius: 10px; }" +
                "h1 { color: #721c24; }" +
                "p { color: #721c24; font-size: 18px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='message-box'>" +
                "<h1>Erro no Pagamento</h1>" +
                "<p>Ocorreu um erro durante a aprovação da compra. Por favor, revise o seu cartão. Se o problema persistir, contate os administradores do sistema.</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

}
