package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.Sale;
import com.gabreudev.marketmobile_api.entities.SaleProduct;
import com.gabreudev.marketmobile_api.repositories.SaleProductRepository;
import com.gabreudev.marketmobile_api.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    SaleProductRepository productSaleRepository;

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
}
