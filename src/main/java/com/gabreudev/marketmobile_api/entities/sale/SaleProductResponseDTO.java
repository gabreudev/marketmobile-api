package com.gabreudev.marketmobile_api.entities.sale;

public record SaleProductResponseDTO(
        String productBarCode,
        String productName,
        Integer quantity,
        Float partialPrice
) {
    public SaleProductResponseDTO(SaleProduct saleProduct) {
        this(
                saleProduct.getProduct().getBarCode(),
                saleProduct.getProduct().getName(),
                saleProduct.getQuantity(),
                saleProduct.getPartialPrice()
        );
    }
}