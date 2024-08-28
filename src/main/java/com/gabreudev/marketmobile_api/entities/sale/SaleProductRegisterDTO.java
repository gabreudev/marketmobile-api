package com.gabreudev.marketmobile_api.entities.sale;

public record SaleProductRegisterDTO(
        String productBarCode,
        Integer quantity,
        Float partialPrice
) {}
