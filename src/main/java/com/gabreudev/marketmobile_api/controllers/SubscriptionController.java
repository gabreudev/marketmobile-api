package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.infra.Config.SecurityConfigurations;
import com.gabreudev.marketmobile_api.servicies.SubscriptionService;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sub")
@Slf4j
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@Tag(name = "Endpoints de inscricao")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<String> createCheckoutSession(@AuthenticationPrincipal User user) {
        try {
            Session session = service.createCheckoutSession(user);
            return ResponseEntity.ok(session.getUrl());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar sessão de checkout: " + e.getMessage());
        }
    }
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription(@AuthenticationPrincipal User user) {
        try {
            service.cancelSubscription(user);
            return ResponseEntity.ok("Assinatura cancelada com sucesso. Redirecionando...");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao cancelar assinatura: " + e.getMessage());
        }
    }
    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(@AuthenticationPrincipal User user) {
        try {
            Customer customer = service.createCustomer(user);

            return ResponseEntity.ok(customer.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar customer para o usuario: " + e.getMessage());
        }
    }
}
