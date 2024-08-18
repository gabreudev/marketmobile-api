package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.Sale;
import com.gabreudev.marketmobile_api.entities.SaleProduct;
import com.gabreudev.marketmobile_api.repositories.SaleProductRepository;
import com.gabreudev.marketmobile_api.repositories.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    SaleProductRepository productSaleRepository;

    @Transactional
    public Long postSale(Sale data){
        Sale savedSale = saleRepository.save(data);

        for (SaleProduct saleProduct : data.getSaleProducts()) {
            saleProduct.setSale(savedSale);
            productSaleRepository.save(saleProduct);
        }
        return savedSale.getId();
    }

    public List<Sale> getAll(){
        return saleRepository.findAll();
    }

    public Long deleteSale(Long id) {
        saleRepository.deleteById(id);
        return id;
    }

    public List<Sale> salesBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return saleRepository.findBySaleDateBetween(startDate, endDate);
    }
}
