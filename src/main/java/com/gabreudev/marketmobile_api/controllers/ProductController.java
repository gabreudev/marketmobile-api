package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.Product;
import com.gabreudev.marketmobile_api.servicies.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("product")
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping()
    public List<Product> getAll(){
        return service.getAll();
    }

}
