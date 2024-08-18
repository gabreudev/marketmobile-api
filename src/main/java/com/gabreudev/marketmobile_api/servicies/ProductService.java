package com.gabreudev.marketmobile_api.servicies;

import com.gabreudev.marketmobile_api.repositories.ProductRepository;
import com.gabreudev.marketmobile_api.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAll(){
        return repository.findAll();
    }

    public String postProduct(Product data){
        repository.save(data);
        return data.getBarCode();
    }

    public String deleteProduct(String barCode) {
        Product deleted = repository.findById(barCode).orElseThrow();
        repository.delete(deleted);
        return deleted.getBarCode();
    }
}
