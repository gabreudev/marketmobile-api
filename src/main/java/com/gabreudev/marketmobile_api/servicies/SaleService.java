package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.product.Product;
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
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;


    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Long postSale(SaleRegisterDTO data, User user) {
        Sale sale = new Sale();
        sale.setUser(user);
        sale.setSaleDate(LocalDateTime.now());
        sale.setTotalPrice(data.totalPrice());

        Sale savedSale = saleRepository.save(sale);

        List<SaleProduct> saleProducts = data.saleProducts().stream()
                .map(dto -> {
                    Product product = productRepository.findByBarCodeAndUser(dto.productBarCode(), user)
                            .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + dto.productBarCode() + " não encontrado"));
                    SaleProduct saleProduct = new SaleProduct(dto, product);
                    if (product.getStock()!=null && product.getStock()!=0) {
                        product.setStock(product.getStock()-saleProduct.getQuantity());
                        productRepository.save(product);
                    }
                    saleProduct.setSale(savedSale);
                    return saleProduct;
                })
                .collect(Collectors.toList());

        sale.setSaleProducts(saleProducts);

        saleRepository.save(savedSale);

        return savedSale.getId();
    }


    public List<SaleResponseDTO> getAllSalesByUser(User user) {
        return saleRepository.findByUser(user).stream()
                .map(SaleResponseDTO::new)
                .toList();
    }

    public Long deleteSale(Long id, User user) {
        Sale sale = saleRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ProductNotFoundException("Venda não encontrada ou você não tem permissão para deletar esta venda."));
        saleRepository.delete(sale);
        return id;
    }

    public List<SaleResponseDTO> salesBetween(LocalDateTime startDate, LocalDateTime endDate, User user) {
        return saleRepository.findBySaleDateBetweenAndUser(startDate, endDate, user).stream()
                .map(SaleResponseDTO::new)
                .toList();
    }
}
