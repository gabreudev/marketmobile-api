package com.gabreudev.marketmobile_api.repositories;

import com.gabreudev.marketmobile_api.entities.saleProduct.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
}
