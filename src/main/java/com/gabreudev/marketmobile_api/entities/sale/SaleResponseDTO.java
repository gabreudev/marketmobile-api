package com.gabreudev.marketmobile_api.entities.sale;

import java.util.List;

public record SaleResponseDTO(
        List<SaleProductResponseDTO> saleProducts,
        Float totalPrice
) {
}
