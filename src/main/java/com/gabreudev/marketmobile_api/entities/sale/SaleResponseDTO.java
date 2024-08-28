package com.gabreudev.marketmobile_api.entities.sale;

import com.gabreudev.marketmobile_api.entities.product.Product;

import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        List<SaleProduct> saleProducts,
        Float totalPrice,
        LocalDateTime date
) {
    public SaleResponseDTO(Sale sale) {
        this(
                sale.getSaleProducts(),
                sale.getTotalPrice(),
                sale.getSaleDate()
        );
    }
}
