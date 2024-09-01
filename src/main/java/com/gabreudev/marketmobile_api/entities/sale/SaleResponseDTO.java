package com.gabreudev.marketmobile_api.entities.sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record SaleResponseDTO(
        List<SaleProductResponseDTO> saleProducts,
        Float totalPrice,
        LocalDateTime date
) {
    public SaleResponseDTO(Sale sale) {
        this(
                sale.getSaleProducts().stream()
                        .map(SaleProductResponseDTO::new)
                        .collect(Collectors.toList()),
                sale.getTotalPrice(),
                sale.getSaleDate()
        );
    }
}
