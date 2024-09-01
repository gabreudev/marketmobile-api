package com.gabreudev.marketmobile_api.repositories;

import com.gabreudev.marketmobile_api.entities.sale.Sale;
import com.gabreudev.marketmobile_api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByUser(User user);
    Optional<Sale> findByIdAndUser(Long id, User user);
    List<Sale> findBySaleDateBetweenAndUser(LocalDateTime startDate, LocalDateTime endDate, User user);
}
