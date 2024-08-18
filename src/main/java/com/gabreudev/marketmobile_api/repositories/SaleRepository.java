package com.gabreudev.marketmobile_api.repositories;

import com.gabreudev.marketmobile_api.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
