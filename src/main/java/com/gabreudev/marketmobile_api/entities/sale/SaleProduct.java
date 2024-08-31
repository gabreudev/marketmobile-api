package com.gabreudev.marketmobile_api.entities.sale;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gabreudev.marketmobile_api.entities.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Random;

@Entity
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_barcode")
    private Product product;

    @NotNull
    private Integer quantity;

    @NotNull
    private Float partialPrice;

    public SaleProduct(SaleProductRegisterDTO dto, Product product) {
        this.product = product;
        this.quantity = dto.quantity();
        this.partialPrice = product.getPrice() * dto.quantity();
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public Float getPartialPrice() {
        return partialPrice;
    }

    public void setPartialPrice(Float partialPrice) {
        this.partialPrice = partialPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
