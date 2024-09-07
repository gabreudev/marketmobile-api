package com.gabreudev.marketmobile_api.entities.product;

public record ProductRegisterDTO(
        String barCode,
        String name,
        String description,
        Float price,
        Integer stock,
        Integer warningStock
) {}
