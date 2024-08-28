package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.infra.Config.SecurityConfigurations;
import com.gabreudev.marketmobile_api.servicies.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("product")
@RestController
@Slf4j
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@Tag(name = "Endpoints de produtos")
public class ProductController {

    @Autowired
    ProductService service;

    // Método para listar todos os produtos do usuário autenticado
    @GetMapping()
    public ResponseEntity<List<Product>> getAll(@AuthenticationPrincipal User user) {
        List<Product> list = service.getAllProductsByUser(user);
        return ResponseEntity.ok(list);
    }

    // Método para adicionar um produto associado ao usuário autenticado
    @PostMapping()
    public ResponseEntity<String> postProduct(@Valid @RequestBody Product data, @AuthenticationPrincipal User user) {
        String codeResponde = service.postProduct(data, user);
        return ResponseEntity.ok(codeResponde);
    }

    // Método para excluir um produto, garantindo que o usuário autenticado seja o proprietário
    @DeleteMapping("{barCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String barCode, @AuthenticationPrincipal User user) {
        String codeBar = service.deleteProduct(barCode, user);
        return ResponseEntity.ok(codeBar);
    }

    // Método para editar um produto, garantindo que o usuário autenticado seja o proprietário
    @PutMapping()
    public ResponseEntity<String> editProduct(@Valid @RequestBody Product data, @AuthenticationPrincipal User user) {
        String codeBar = service.editProduct(data, user);
        return ResponseEntity.ok(codeBar);
    }
}
