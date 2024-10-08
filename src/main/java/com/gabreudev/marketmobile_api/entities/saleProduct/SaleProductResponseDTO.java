package com.gabreudev.marketmobile_api.entities.saleProduct;


public record SaleProductResponseDTO(
        String productBarCode,
        String productName,
        Integer quantity,
        Float partialPrice
) {
    public SaleProductResponseDTO(SaleProduct saleProduct) {
        this(
                saleProduct.getProductBarCode(),
                saleProduct.getProductName(),
                saleProduct.getQuantity(),
                saleProduct.getPartialPrice()
        );
    }
}