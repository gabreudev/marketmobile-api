package com.gabreudev.marketmobile_api.repositories;

import com.gabreudev.marketmobile_api.entities.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
