package com.gabreudev.marketmobile_api.controllers;

import com.gabreudev.marketmobile_api.entities.Sale;
import com.gabreudev.marketmobile_api.servicies.SaleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("sale")
@Slf4j
@Tag(name = "Endpoints de vendas")
public class SaleController {

    @Autowired
    SaleService service;

    @PostMapping
    @Description("metodo que cadastra uma venda")
    public Long postSale(@RequestBody Sale data){
        return service.postSale(data);
    }

    @GetMapping()
    @Description("retorna todas as vendas feitas")
    public List<Sale> getAll(){
        return service.getAll();
    }
    @DeleteMapping("{id}")
    @Description("deleta uma venda pelo codigo de barras")
    public Long deleteSale(@PathVariable Long id){
        return service.deleteSale(id);
    }
    @GetMapping("sale/between")
    @Description("metodo que retorna todas as vendas entre um determinado periodo")
    public List<Sale> salesBetween(@RequestParam LocalDateTime startDate,
                                   @RequestParam LocalDateTime endDate){
        return service.salesBetween(startDate, endDate);
    }
}
