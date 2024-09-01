package com.gabreudev.marketmobile_api.entities.product;

public record ProductResponseDTO(
        String barCode,
        String name,
        String description,
        Float price
        ) {
        public ProductResponseDTO(Product product) {
                this(
                        product.getBarCode(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                );
        }
}
