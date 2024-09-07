package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.entities.product.Product;
import com.gabreudev.marketmobile_api.entities.product.ProductRegisterDTO;
import com.gabreudev.marketmobile_api.entities.product.ProductResponseDTO;
import com.gabreudev.marketmobile_api.entities.user.User;
import com.gabreudev.marketmobile_api.exceptions.ProductAlreadyExistsException;
import com.gabreudev.marketmobile_api.exceptions.ProductNotFoundException;
import com.gabreudev.marketmobile_api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<ProductResponseDTO> getAllProductsByUser(User user) {
        List<ProductResponseDTO> products = repository.findByUser(user).stream().map(ProductResponseDTO::new).toList();
        return products;
    }

    public String postProduct(ProductRegisterDTO data, User user) {
        Product product = new Product(data);
        product.setUser(user);
        if (repository.findByBarCodeAndUser(product.getBarCode(), user).isPresent()){
            throw new ProductAlreadyExistsException("Já existe um produto cadastrado com esse codigo de barras.");
        }
        repository.save(product);
        return product.getBarCode();
    }

    public String deleteProduct(String barCode, User user) {
        Product deleted = repository.findByBarCodeAndUser(barCode, user)
                .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + barCode + " não encontrado"));
        repository.delete(deleted);
        return deleted.getBarCode();
    }

    public String editProduct(ProductRegisterDTO data, User user) {
        Product product = new Product(data);
        Product existingProduct = repository.findByBarCodeAndUser(product.getBarCode(), user)
                .orElseThrow(() -> new ProductNotFoundException("Produto com código de barras " + product.getBarCode() + " não encontrado"));

        existingProduct.setName(product.getName());
        existingProduct.setStock(product.getStock());
        existingProduct.setWarningStock(product.getWarningStock());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        return repository.save(existingProduct).getBarCode();
    }
}
