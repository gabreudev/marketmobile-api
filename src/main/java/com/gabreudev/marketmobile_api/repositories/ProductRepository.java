package com.gabreudev.marketmobile_api.repositories;

import com.gabreudev.marketmobile_api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
