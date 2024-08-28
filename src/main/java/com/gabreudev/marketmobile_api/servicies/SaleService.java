package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.entities.product.ProductResponseDTO;
import com.gabreudev.marketmobile_api.entities.sale.*;
import com.gabreudev.marketmobile_api.entities.user.User;
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
    public Long postSale(SaleRegisterDTO data, User user){
        Sale sale = new Sale(data);
        sale.setUser(user);

        for (SaleProduct saleProduct : sale.getSaleProducts()) {
            String barCode = saleProduct.getProduct().getBarCode();
            Product product = productRepository.findById(barCode)
                    .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + barCode + " não encontrado"));

            saleProduct.setProduct(product);
        }
        sale.setSaleDate(LocalDateTime.now());
        Sale savedSale = saleRepository.save(sale);
        return savedSale.getId();
    }

    public List<SaleResponseDTO> getAllSalesByUser(User user) {
        List<SaleResponseDTO> sales = saleRepository.findByUser(user).stream().map(SaleResponseDTO::new).toList();
        return sales;
    }

    public Long deleteSale(Long id, User user) {
        Sale sale = saleRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ProductNotFoundException("Venda não encontrada ou você não tem permissão para deletar esta venda."));
        saleRepository.delete(sale);
        return id;
    }

    public List<SaleResponseDTO> salesBetween(LocalDateTime startDate, LocalDateTime endDate, User user) {
        return saleRepository.findBySaleDateBetweenAndUser(startDate, endDate, user).stream().map(SaleResponseDTO::new).toList();
    }
}