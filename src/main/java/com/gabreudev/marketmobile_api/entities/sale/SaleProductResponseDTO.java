package com.gabreudev.marketmobile_api.entities.sale;

import java.util.UUID;

public record SaleProductResponseDTO(
        UUID id,
        String productBarCode,
        String productName,
        Integer quantity,
        Float partialPrice
) {
    public SaleProductResponseDTO(SaleProduct saleProduct) {
        this(
                saleProduct.getProduct().getId(),
                saleProduct.getProduct().getBarCode(),
                saleProduct.getProduct().getName(),
                saleProduct.getQuantity(),
                saleProduct.getPartialPrice()
        );
    }
}