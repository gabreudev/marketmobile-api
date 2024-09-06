package com.gabreudev.marketmobile_api.entities.sale;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gabreudev.marketmobile_api.entities.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_saleProduct;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;

    @NotNull
    private String productBarCode;

    private String productName;

    private Float productPrice;

    @NotNull
    private Integer quantity;

    @NotNull
    private Float partialPrice;

    public SaleProduct(SaleProductRegisterDTO dto, Product product) {
        this.productBarCode = product.getBarCode();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.quantity = dto.quantity();
        this.partialPrice = dto.partialPrice();
    }

    public SaleProduct(){}

    // Getters e Setters

    public String getProductBarCode() {
        return productBarCode;
    }

    public void setProductBarCode(String productBarCode) {
        this.productBarCode = productBarCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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

    public Long getId() {
        return id_saleProduct;
    }

    public void setId(Long id) {
        this.id_saleProduct = id;
    }
}
