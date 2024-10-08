package com.gabreudev.marketmobile_api.entities.product;

import java.util.UUID;

public record ProductResponseDTO(
        UUID id_product,
        String barCode,
        String name,
        String description,
        Float price,
        Integer stock,
        Integer warningStock
        ) {
        public ProductResponseDTO(Product product) {
                this(
                        product.getId(),
                        product.getBarCode(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStock(),
                        product.getWarningStock()
                );
        }
}
