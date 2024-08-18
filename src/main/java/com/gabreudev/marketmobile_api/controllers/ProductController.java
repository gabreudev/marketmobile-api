package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.Product;
import com.gabreudev.marketmobile_api.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("product")
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping()
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping()
    public String postProduct(@RequestBody Product data) {
        return service.postProduct(data);
    }

    @DeleteMapping("{barCode}")
    public String deleteProduct(@PathVariable String barCode) {
        return service.deleteProduct(barCode);
    }

    @PutMapping()
    public String editProduct(@RequestBody Product data) {
        return service.editProduct(data);
    }

}
