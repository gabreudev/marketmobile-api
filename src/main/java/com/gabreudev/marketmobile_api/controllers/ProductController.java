package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.infra.Config.SecurityConfigurations;
import com.gabreudev.marketmobile_api.servicies.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        List<Product> list= service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping()
    public ResponseEntity<String> postProduct(@Valid @RequestBody Product data) {
        String codeResponde = service.postProduct(data);
        return ResponseEntity.ok(codeResponde);
    }

    @DeleteMapping("{barCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String barCode) {
        String codeBar = service.deleteProduct(barCode);
        return ResponseEntity.ok(codeBar);

    }

    @PutMapping()
    public ResponseEntity<String> editProduct(@Valid @RequestBody Product data) {
        String codeBar = service.editProduct(data);
        return ResponseEntity.ok(codeBar);
    }

}
