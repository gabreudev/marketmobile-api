package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.servicies.SubscriptionService;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sub")
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
