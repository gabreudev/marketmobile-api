package com.gabreudev.marketmobile_api.entities.saleProduct;

public record SaleProductRegisterDTO(
        String productBarCode,
        Integer quantity,
        Float partialPrice
) {}

