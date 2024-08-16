package com.gabreudev.marketmobile_api.Repositories;

import com.gabreudev.marketmobile_api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
