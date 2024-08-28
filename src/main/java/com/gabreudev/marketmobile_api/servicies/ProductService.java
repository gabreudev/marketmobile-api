package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.exceptions.ProductNotFoundException;
import com.gabreudev.marketmobile_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProductsByUser(User user) {
        return repository.findByUser(user);
    }

    public String postProduct(Product data, User user) {
        data.setUser(user);
        repository.save(data);
        return data.getBarCode();
    }

    public String deleteProduct(String barCode, User user) {
        Product deleted = repository.findByBarCodeAndUser(barCode, user)
                .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + barCode + " não encontrado"));
        repository.delete(deleted);
        return deleted.getBarCode();
    }

    public String editProduct(Product data, User user) {
        Product existingProduct = repository.findByBarCodeAndUser(data.getBarCode(), user)
                .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + data.getBarCode() + " não encontrado"));

        existingProduct.setName(data.getName());
        existingProduct.setDescription(data.getDescription());
        existingProduct.setPrice(data.getPrice());

        return repository.save(existingProduct).getBarCode();
    }
}
