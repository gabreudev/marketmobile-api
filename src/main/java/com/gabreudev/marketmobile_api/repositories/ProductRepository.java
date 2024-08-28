package com.gabreudev.marketmobile_api.repositories;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByUser(User user);
    Optional<Product> findByBarCodeAndUser(String barCode, User user);
}
