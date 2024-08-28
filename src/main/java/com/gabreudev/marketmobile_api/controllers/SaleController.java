package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.sale.SaleRegisterDTO;
import com.gabreudev.marketmobile_api.entities.sale.SaleResponseDTO;
import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.infra.Config.SecurityConfigurations;
import com.gabreudev.marketmobile_api.servicies.SaleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("sale")
@Slf4j
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@Tag(name = "Endpoints de vendas")
public class SaleController {

    @Autowired
    SaleService service;

    @PostMapping
    @Description("Método que cadastra uma venda")
    public ResponseEntity<Long> postSale(@RequestBody SaleRegisterDTO data, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(service.postSale(data, user));
    }

    @GetMapping()
    @Description("Retorna todas as vendas feitas pelo usuário autenticado")
    public ResponseEntity<List<SaleResponseDTO>> getAll(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(service.getAllSalesByUser(user));
    }

    @DeleteMapping("{id}")
    @Description("Deleta uma venda pelo ID, se o usuário autenticado a tiver criado")
    public ResponseEntity<Long> deleteSale(@PathVariable Long id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(service.deleteSale(id, user));
    }

    @GetMapping("between")
    @Description("Método que retorna todas as vendas feitas entre um determinado período pelo usuário autenticado")
    public ResponseEntity<List<SaleResponseDTO>> salesBetween(@RequestParam LocalDateTime startDate,
                                                   @RequestParam LocalDateTime endDate,
                                                   @AuthenticationPrincipal User user){
        return ResponseEntity.ok(service.salesBetween(startDate, endDate, user));
    }
}