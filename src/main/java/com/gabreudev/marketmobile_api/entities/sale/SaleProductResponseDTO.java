package com.gabreudev.marketmobile_api.entities.sale;

public record SaleProductResponseDTO(
        Long id,
        String productBarCode,
        String productName,
        Integer quantity,
        Float partialPrice
) {}
