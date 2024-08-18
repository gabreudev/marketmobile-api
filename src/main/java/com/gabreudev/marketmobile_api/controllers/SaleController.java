package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.Sale;
import com.gabreudev.marketmobile_api.servicies.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sale")
public class SaleController {

    @Autowired
    SaleService service;

    @PostMapping
    public Long postSale(@RequestBody Sale data){
        return service.postSale(data);
    }

    @GetMapping()
    public List<Sale> getAll(){
        return service.getAll();
    }
}
