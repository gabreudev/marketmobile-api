package com.gabreudev.marketmobile_api.entities.sale;

import java.util.List;

public record SaleRegisterDTO(
        List<SaleProduct> saleProducts,
        Float totalPrice
) {}