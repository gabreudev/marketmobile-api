package com.gabreudev.marketmobile_api.entities.sale;

public record SaleProductResponseDTO(
        String productBarCode,
        String productName,
        Integer quantity,
        Float partialPrice
) {

}