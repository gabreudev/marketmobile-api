package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.entities.sale.Sale;
import com.gabreudev.marketmobile_api.entities.sale.SaleProduct;
import com.gabreudev.marketmobile_api.exceptions.ProductNotFoundException;
import com.gabreudev.marketmobile_api.repositories.ProductRepository;
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

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Long postSale(Sale data){

        for (SaleProduct saleProduct : data.getSaleProducts()) {
            String barCode = saleProduct.getProduct().getBarCode();
            Product product = productRepository.findById(barCode)
                    .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + barCode + " não encontrado"));

            saleProduct.setProduct(product);
            saleProduct.setPartialPrice(product.getPrice() * saleProduct.getQuantity());
        }
        data.setSaleDate(LocalDateTime.now());
        Sale savedSale = saleRepository.save(data);
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
