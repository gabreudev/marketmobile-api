package com.gabreudev.marketmobile_api.entities.sale;

import com.gabreudev.marketmobile_api.entities.saleProduct.SaleProductRegisterDTO;

import java.util.List;

public record SaleRegisterDTO(
        List<SaleProductRegisterDTO> saleProducts,
        Float totalPrice,
        Float discount
) {}