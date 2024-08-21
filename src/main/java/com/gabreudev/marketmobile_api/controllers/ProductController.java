package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.Product;
import com.gabreudev.marketmobile_api.exceptions.ProductNotFoundException;
import com.gabreudev.marketmobile_api.servicies.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("product")
@RestController
@Slf4j
@Tag(name = "Endpoints de produtos")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping()
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping()
    public String postProduct(@Valid @RequestBody Product data) {
        return service.postProduct(data);
    }

    @DeleteMapping("{barCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String barCode) {
        String codeBar = service.deleteProduct(barCode);
        return ResponseEntity.ok(codeBar);

    }

    @PutMapping()
    public String editProduct(@Valid @RequestBody Product data) {
        return service.editProduct(data);
    }

}
