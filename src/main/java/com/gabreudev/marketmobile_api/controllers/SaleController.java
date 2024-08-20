package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.Sale;
import com.gabreudev.marketmobile_api.servicies.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @DeleteMapping("{id}")
    public Long deleteSale(@PathVariable Long id){
        return service.deleteSale(id);
    }
    @GetMapping("sale/between")
    public List<Sale> salesBetween(@RequestParam LocalDateTime startDate,
                                   @RequestParam LocalDateTime endDate){
        return service.salesBetween(startDate, endDate);
    }
}
